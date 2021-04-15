package org.iskon.services;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.iskon.models.Event;
import org.iskon.models.EventSearch;
import org.iskon.models.Notification;
import org.iskon.models.NotificationApproval;
import org.iskon.models.NotificationSearch;
import org.iskon.models.NotificationTracker;
import org.iskon.models.NotificationUi;
import org.iskon.models.Team;
import org.iskon.repositories.NotificationApprovalJpaRepository;
import org.iskon.repositories.NotificationJpaRepository;
import org.iskon.repositories.NotificationTrackerJpaRepository;
import org.iskon.repositories.UserTokenJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class NotificationService {
	@Autowired
	private NotificationJpaRepository ntfDb;
	@Autowired
	private NotificationApprovalJpaRepository ntfApprovalDb;
	@Autowired
	private UserTokenJpaRepository userTokenDb;
	@Autowired
	NotificationTrackerJpaRepository ntfTrackerRepo;

	@Autowired
	UserService userService;
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	EventService eventService;
	
	public NotificationApproval getNotificationById(int id){
		return ntfApprovalDb.findById(id);
	}
	
	public void deleteNotification(NotificationApproval newNtf)
	{
		ntfApprovalDb.delete(newNtf);
	}
	
	/** Service call to fetch all the notifications.
	 * @param username The unique email id of the user as stored and defined in the user table.
	 * @return List<NotificationUi\> List of objects modified to fit the model used on the UI.
	 * */
	public	List<NotificationUi> getAll(String username) {
		List<NotificationUi> result = ntfApprovalDb.findByUserName( username );
		result.addAll(ntfDb.findByUserName(username));
		return result; 
	}

	/**
	 * 
	 * @param ntf Notification data to be persisted in the database and that needs to be sent to the end user
	 * @param userIds List of unique userId's corresponding to valid users as defined in the user database , that needs to be sent the notification to.
	 * @return Boolean true or false value depicting the success or failure of operation
	 */
	private Boolean handleNotification(Notification ntf,List<Integer> userIds) {	
		try {
			Notification savedNtf = ntfDb.save(ntf); //store the notifications
			Map<String,String> ntfData= new HashMap<>(); 
			for(int userId : userIds) { 
				NotificationTracker ntfTracker = new NotificationTracker(userId, ntf.getTargetId(),
						savedNtf.getUuid().toString());
				List<String> token = userTokenDb.findDeviceTokenByUserId(userId); //get the device token from the user_token database
				if (token == null) {
					System.out.println("No token found for the given userId! Thus no notification can be sent to"
							+ "the user.");
					ntfTracker.setStatus(false); //set sent status to false if there was no token found for the given user.
				}
				else{
					FireBaseMessagingService fcm = new FireBaseMessagingService(); // used to invoke firebase cloud messaging admin function to send push notifications
					ntfData.put("title", savedNtf.getTitle());
					ntfData.put("message",savedNtf.getMessage());
					Boolean flag = false;
					String response="";
					for(String s : token) {
						if( s!=null)
							response = fcm.sendToUser(ntfData, s);
						if(response != null) flag = true;  					//for every token found if there's an invalid token then delete it otherwise send the notification to  the deviceToken
						else {												// firebase messaging service returns null if there's an error sending to a specific deviceToken which means that either the 
																			//token has expired or is incorrect.	
							userTokenDb.deleteDeviceTokenById(userId,s);
						}
					}
					ntfTracker.setStatus(flag);
				}
				ntfTrackerRepo.save(ntfTracker);  //save data in the notification_tracker database
			}
			return true;
		}
		catch(Exception err) {
			System.out.println("Error at HandleNotification"+err.getMessage());
			err.printStackTrace();;
			return false;
		}
		
	}
	/**
	 * The function is very similar to the handleNotification method, except this one is tailored to handle notification_approval type data
	 * @param ntf 	Notification data corresponding to NotificationApproval model as defined in the model package .
	 * @param userIds List of unique userId's corresponding to valid users as defined in the user database , that needs to be sent the notification to.
	 * @return Boolean true or false value depicting the success or failure of operation
	 */
	private Boolean handleNotificationApproval(NotificationApproval ntf,List<Integer> userIds ) {
		NotificationApproval savedNtf = ntfApprovalDb.save(ntf);
		System.out.println(savedNtf.getUuid());
		System.out.println(userIds);
		for(int userId : userIds) {
			List<String> token = userTokenDb.findDeviceTokenByUserId(userId);
			NotificationTracker ntfTracker = new NotificationTracker(userId, ntf.getTargetId() , savedNtf.getUuid().toString()); 
			if (token == null) {
				System.out.println("No token found for the given userId! Thus no notification can be sent to the user.");
				ntfTracker.setStatus(false);
			}
			else {
				FireBaseMessagingService fcm = new FireBaseMessagingService();
				Map<String,String> ntfData = new HashMap<>();
				ntfData.put("title",savedNtf.getTitle());
				ntfData.put("message",savedNtf.getMessage());
				Boolean flag = false;
				String response="";
				for(String s : token) {
					if( s != null )
					response = fcm.sendToUser(ntfData, s);   // given notification is successfully sent to any of the tokens , the sent status for that user is set to true.
															//valid tokens are send notifications and invalid tokens are deleted.
					if(response != null) flag = true; 
				}
				ntfTracker.setStatus(flag);
			}
			ntfTrackerRepo.save(ntfTracker);
		}
		 return true;
	}

	/**
	 * 
	 * @param ntf Notification data corresponding to the notification model from the model package
	 * @return Boolean true or false value depicting the success or failure of operation
	 */
		public Boolean saveNotification(Notification ntf) {
			if (ntf.getBroadcastType().equalsIgnoreCase("single")) { // use single whenever	the intended recipient is a single entity
				List<Integer> ids = new ArrayList<>();
				ids.add(ntf.getTargetId());
				return handleNotification(ntf,ids);
			}
			else if (ntf.getBroadcastType().equals("multiple")) {
			String tableToQuery = ntf.getTargetType();
				if(tableToQuery == "") {
					System.out.println("Required Mapping data");
					return false;
				}
				if (tableToQuery.equalsIgnoreCase("event")) {  // multiple + event will send notification to all people registered for the event
					System.out.println("Method called");
					ntf.setTitle("Kirtan Event Updates");
					List<Integer> userId = ntfDb.getParticipants(ntf.getTargetId());
					System.out.println(userId);
					return handleNotification(ntf, userId);
				} else if (tableToQuery.equalsIgnoreCase("team")) {
						ntf.setTitle("Kirtan Team Updates");
						ArrayList<Integer> teamId = new ArrayList<>();
						teamId.add(ntf.getTargetId());
						List<Integer> userId = ntfDb.getTeamMemberId(teamId);
						//List<Integer> userId = ntfDb.getTeamLeadId(teamService.getTeamById(teamId).getTeamLeadId());
						return handleNotification(ntf, userId);
					} else if(tableToQuery.equalsIgnoreCase("user_temple")){
						ntf.setTitle("Kirtan Admin Updates");					
						List<Integer> adminIds = new ArrayList<>();
						Event event = eventService.getEventById(ntf.getTargetId());
						//trial function
						adminIds.addAll(ntfDb.getTeamLead(event.getEventType()));
						
						//complete function
						//Getting week day
						// if(!event.getPublicEvent()) {
//				        String dayWeekText = new SimpleDateFormat("EEEE").format(event.getEventDate());
						//adminIds.addAll(ntfDb.getTeamLead(event.getEventType(),event.getEventLocation(),event.getEventTime(),dayWeekText); }
						return handleNotification(ntf,adminIds);
					} else{
						System.out.println("Incorrect mapping");
						return false;
					}
			}else{
				System.out.println("Invalid target option");
				return false;
			}
	}

	/**
	 * @param ntf Notification data corresponding to the notification_approval model from the model package
	 * @return Boolean true or false value depicting the success or failure of operation
	 */
	public Boolean saveNotificationAppr(NotificationApproval ntf) {
			ntf.setTitle("Kirtan Admin Updates");
			ntf.setAction("waiting");
			Event event = eventService.getEventById(ntf.getTargetId());
			
			System.out.println(event.getEventType());
			List<Integer> adminIds = ntfDb.getTeamLead(eventService.getEventById(ntf.getTargetId()).getEventType());
			//complete function
			//Getting week day
			// if(!event.getPublicEvent()) {
//	        String dayWeekText = new SimpleDateFormat("EEEE").format(event.getEventDate());
			//adminIds.addAll(ntfDb.getTeamLead(event.getEventType(),event.getEventLocation(),event.getEventTime(),dayWeekText); }
			return handleNotificationApproval(ntf, adminIds);			
	}
	

	
	/**
	 * 
	 * @param status	response from the admin whether approved or rejected
	 * @param ntfId		Unique universal id for the notification
	 * @param username	email address for the user
	 * @return NotificationApproval notification data corresponding to the NotificationApprovalModel from the model package	
	 */
	public NotificationApproval updateApproval(String status,String ntfId,String username) {
		NotificationApproval ntfToBeUpdated = ntfApprovalDb.findNotificationApprovalByUuid(ntfId); 
		if(ntfToBeUpdated == null) {
			System.out.println("Notification to be updated does not exist");
			return null;
		}
		ntfToBeUpdated.setAction(status); //action status not fetched?
		ntfToBeUpdated.setUpdatedBy(username);
		ntfToBeUpdated.setUpdatedTime(new Date());
		NotificationApproval updatedNotification = ntfApprovalDb.save(ntfToBeUpdated);
		Notification newNtf = new Notification();
		newNtf.setMessage("Your previous request has been "+ updatedNotification.getAction()+"("+updatedNotification.getMessage()+")");
		newNtf.setBroadcastType("single");		
		newNtf.setTargetType("user");
		newNtf.setTargetId(userService.getUserByEmailId(updatedNotification.getUpdatedBy()).get().getId()); //No value fetched for update/edit ntfs
		newNtf.setMappingTableData(updatedNotification.getMappingTableData());
		newNtf.setUpdatedBy(updatedNotification.getUpdatedBy()); 
		newNtf.setUpdatedTime(new Date());
		newNtf.setCreatedBy(updatedNotification.getUpdatedBy());
		newNtf.setCreatedTime(new Date());
		newNtf.setUuid(UUID.randomUUID());
		newNtf.setTitle("Status Update");
		List<Integer> userIds = new ArrayList<Integer>();
		userIds.add(newNtf.getTargetId());
		if(!this.handleNotification(newNtf, userIds))
			System.out.println("Couldnt send message");
		return updatedNotification;
	}

	
	public List<NotificationApproval> getntf(NotificationSearch eventSearch)
	{
		return ntfApprovalDb.findAll(new Specification<NotificationApproval>(){

			@Override
			public Predicate toPredicate(Root<NotificationApproval> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				
				ZoneId defaultZoneId = ZoneId.systemDefault();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
				if (eventSearch.getDateInterval() != null) {
					LocalDate todaydate=LocalDate.now();
					String eventDate= dateTimeFormatter.format(todaydate);  
					LocalDate startDate = LocalDate.parse(eventDate);
					int dayOffSet = 0;
					if (eventSearch.getDateInterval().equals("TODAY")) {
						dayOffSet = 1;
						LocalDate endDate = startDate.plusDays(dayOffSet);
						String end = endDate.toString();
						Date s=java.sql.Date.valueOf(startDate);
						Date e=java.sql.Date.valueOf(end);
						System.out.println(e);
						System.out.println(s);
						predicates.add(criteriaBuilder.between(root.get("createdTime"), s,e));}
					else if(eventSearch.getDateInterval().equals("NOT TODAY")) {
						dayOffSet = 1;
						LocalDate endDate = startDate.minusDays(dayOffSet);
						String end = endDate.toString();
						Date s=java.sql.Date.valueOf(startDate);
						Date e=java.sql.Date.valueOf(end);
						System.out.println(e);
						System.out.println(s);
						predicates.add(criteriaBuilder.between(root.get("createdTime"), e,s));
					}
				}

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	//cancel invite
	public boolean saveNotificationCancel(Notification ntf) {
		ntf.setTitle("Kirtan Cancel Invite");
		Team team = teamService.getTeamById(ntfDb.getTeamLeadId(ntf.getTargetId()));
		List<Integer> adminIds = new ArrayList<>();
		adminIds.add(team.getTeamLead());
		return handleNotification(ntf, adminIds);
	}
}

package org.iskon.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
import org.iskon.models.EventTeam;
import org.iskon.models.Notification;
import org.iskon.models.NotificationApproval;
import org.iskon.models.NotificationSearch;
import org.iskon.models.NotificationTracker;
import org.iskon.models.NotificationUi;
import org.iskon.models.Team;
import org.iskon.repositories.EventTeamJpaRepository;
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
	
	@Autowired
	EventTeamService eventTeamService;
	
	public NotificationApproval getNotificationById(int id){
		return ntfApprovalDb.findById(id);
	}
	
	public void deleteNotificationAppr(NotificationApproval newNtf)
	{
		ntfApprovalDb.delete(newNtf);
	}
	
	public void deleteNotification(Notification newNtf)
	{
		ntfDb.delete(newNtf);
	}
	
	/** Service call to fetch all the notifications.
	 * @param username The unique email id of the user as stored and defined in the user table.
	 * @return List<NotificationUi\> List of objects modified to fit the model used on the UI.
	 * */
	public	List<NotificationUi> getAll(String username) {
		List<NotificationUi> result = ntfApprovalDb.findByUserName( username );
		result.addAll(ntfDb.findByUserName(username));
		result.sort(Comparator.comparing(NotificationUi::getUpdatedTime));
		Collections.reverse(result);
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
						savedNtf.getUuid().toString(), 0);
				ntfTracker.setStatus(false);
				//List<String> token = userTokenDb.findDeviceTokenByUserId(userId);
				//System.out.println(token);//get the device token from the user_token database
//				if (token == null) {
//					System.out.println("No token found for the given userId! Thus no notification can be sent to"
//							+ "the user.");
//					ntfTracker.setStatus(false); //set sent status to false if there was no token found for the given user.
//				}
//				else{
//					FireBaseMessagingService fcm = new FireBaseMessagingService(); // used to invoke firebase cloud messaging admin function to send push notifications
//					ntfData.put("title", savedNtf.getTitle());
//					ntfData.put("message",savedNtf.getMessage());
//					Boolean flag = false;
//					String response="";
//					for(String s : token) {
//						if( s!=null)
//							response = fcm.sendToUser(ntfData, s);
//						if(response != null) flag = true;  					//for every token found if there's an invalid token then delete it otherwise send the notification to  the deviceToken
//						else {												// firebase messaging service returns null if there's an error sending to a specific deviceToken which means that either the 
//																			//token has expired or is incorrect.	
//							userTokenDb.deleteDeviceTokenById(userId,s);
//						}
//					}
//					ntfTracker.setStatus(flag);
//				}
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
	private Boolean handleNotificationApproval(NotificationApproval ntf,List<Integer> userIds, List<Integer> teamIds ) {
		
		NotificationApproval savedNtf = ntfApprovalDb.save(ntf); 
		System.out.println(teamIds);	
		System.out.println(savedNtf.getUuid());
		System.out.println(userIds);
		
		for(int i =0; i< userIds.size(); i++) {
//			List<String> token = userTokenDb.findDeviceTokenByUserId(userIds.get(i));
//			System.out.println(token);
			NotificationTracker ntfTracker = new NotificationTracker(userIds.get(i), ntf.getTargetId() , savedNtf.getUuid().toString(), teamIds.get(i)); 
//			if (token == null) {
//				System.out.println("No token found for the given userId! Thus no notification can be sent to the user.");
//				ntfTracker.setStatus(false);
//			}
//			else {
//				FireBaseMessagingService fcm = new FireBaseMessagingService();
//				Map<String,String> ntfData = new HashMap<>();
//				ntfData.put("title",savedNtf.getTitle());
//				ntfData.put("message",savedNtf.getMessage());
//				Boolean flag = false;
//				String response="";
//				for(String s : token) {
//					if( s != null )
//					response = fcm.sendToUser(ntfData, s);   // given notification is successfully sent to any of the tokens , the sent status for that user is set to true.
//															//valid tokens are send notifications and invalid tokens are deleted.
//					if(response != null) flag = true; 
//				}
	//			ntfTracker.setStatus(flag);
	//		}
			ntfTracker.setStatus(false);
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
			if (ntf.getBroadcastType().equalsIgnoreCase("single") || ntf.getBroadcastType().equalsIgnoreCase("edit")) { // use single whenever	the intended recipient is a single entity
				List<Integer> ids = new ArrayList<>();
				ids.add(ntf.getTargetId());
				System.out.println(ids);
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
						//List<Integer> userId = userService.getUserByEmailId(ntfDb.getTeamLeadId(teamService.getTeamById(teamId).getTeamLeadId())).get().getId();
						return handleNotification(ntf, userId);
					} else if(tableToQuery.equalsIgnoreCase("user_temple")){
						ntf.setTitle("Kirtan Admin Updates");					
						List<Integer> adminIds = new ArrayList<>();
						Event event = eventService.getEventById(ntf.getTargetId());
						String dayWeekText = new SimpleDateFormat("EEEE").format(event.getEventDate()); //event day
						LocalDate todaydate=LocalDate.now();
						LocalDate eventDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(event.getEventDate()) );
						System.out.println(eventDate);
						Period period = Period.between ( todaydate , eventDate);
						Integer daysElapsed = period.getDays ();
						List<Integer> teamId = ntfDb.getTeamId(event.getEventType(),event.getCity(),Integer.valueOf(event.getEventDuration()),daysElapsed);
						
						for(int i=0; i<teamId.size();i++) {
							Team team = teamService.getTeamById(teamId.get(i));
							adminIds.add(userService.getUserByEmailId(team.getTeamLeadId()).get().getId());
//							
//							if(team.getWeekDay().equalsIgnoreCase("Anyday")){
//								adminIds.add(userService.getUserByEmailId(team.getTeamLeadId()).get().getId());
//							}else if(team.getWeekDay().equalsIgnoreCase(dayWeekText)){
//								adminIds.add(userService.getUserByEmailId(team.getTeamLeadId()).get().getId());
//							}
						}			
						
						//complete function
						//Getting week day
						// if(!event.getPublicEvent()) {
//				        String dayWeekText = new SimpleDateFormat("EEEE").format(event.getEventDate());
						//adminIds.addAll(userService.getUserByEmailId(ntfDb.getTeamLeadId(teamService.getTeamById(teamId).getTeamLeadId())).get().getId()); }
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
			List<Integer> adminIds = new ArrayList<>();
			List<Integer> tempIds = new ArrayList<>();
			if(ntf.getTargetType().equalsIgnoreCase("event")) {
				Event event = eventService.getEventById(ntf.getTargetId());
				String dayWeekText = new SimpleDateFormat("EEEE").format(event.getEventDate());
				LocalDate todaydate=LocalDate.now();
				LocalDate eventDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(event.getEventDate()) );
				System.out.println(eventDate);
				Period period = Period.between ( todaydate , eventDate);
				Integer daysElapsed = period.getDays ();
				System.out.println(daysElapsed);
				//event day
				List<Integer> teamId = ntfDb.getTeamId(event.getEventType(),event.getCity(), Integer.valueOf(event.getEventDuration()), daysElapsed);
				//String eventStartTime = new SimpleDateFormat("HH:mm").parse(event.getEventTime());
//				Date teamStartTime=null, teamEndTime=null, time1 = null, time2=null;
//				try {
//					time1 = new SimpleDateFormat("HH:mm").parse(event.getEventTime());
//					Calendar cal =Calendar.getInstance();
//					cal.setTime(time1);
//					cal.add(Calendar.HOUR_OF_DAY,Integer.parseInt(event.getEventDuration())); // this will add two hours
//					time2 = cal.getTime();
//					System.out.println(time1);
//					System.out.println(time2);
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
//
				for(int i=0; i<teamId.size();i++) {
					Team team = teamService.getTeamById(teamId.get(i));
					adminIds.add(userService.getUserByEmailId(team.getTeamLeadId()).get().getId());
					ntf.setTargetTeamId(teamId.get(i));
					tempIds.add(teamId.get(i));
//					try {
////						teamStartTime =  new SimpleDateFormat("HH:mm").parse(team.getAvailableFrom());
////						teamEndTime = new SimpleDateFormat("HH:mm").parse(team.getAvailableTo());
////						System.out.println(teamStartTime);
////						System.out.println(teamEndTime);
//						
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					if(team.getWeekDay().equalsIgnoreCase("Anyday") && (time1.after(teamStartTime) || time1.equals(teamStartTime)) && (time2.before(teamEndTime)||time2.equals(teamEndTime))) {
//					//if(team.getWeekDay().equalsIgnoreCase("Anyday") ){
//						
//						adminIds.add(userService.getUserByEmailId(team.getTeamLeadId()).get().getId());
//						tempIds.add(teamId.get(i));
//						
//					}else if(team.getWeekDay().equalsIgnoreCase(dayWeekText) && (time1.after(teamStartTime) || time1.equals(teamStartTime)) && (time2.before(teamEndTime)||time2.equals(teamEndTime))) {
//					//else if(team.getWeekDay().equalsIgnoreCase(dayWeekText)){
//						adminIds.add(userService.getUserByEmailId(team.getTeamLeadId()).get().getId());
//						//ntf.setTargetTeamId(teamId.get(i));
//						tempIds.add(teamId.get(i));
//					}
				}					
				
			} else if(ntf.getTargetType().equalsIgnoreCase("team")){
				adminIds = ntfDb.getLocalAdminId(teamService.getTeamById(ntf.getTargetId()).getLocalAdminName());
				tempIds.add(ntf.getTargetId());
			}else {
				adminIds = ntfDb.getAdminId();
				ntf.setTargetTeamId(0);
				//List<Integer> adminIds = ntfDb.getAdminId(ntf.targetId());
			}
			//complete function
			//Getting week day
			// if(event.getPublicEvent()==0) {
//	        String dayWeekText = new SimpleDateFormat("EEEE").format(event.getEventDate());
			//adminIds.addAll(ntfDb.getTeamLead(event.getEventType(),event.getEventLocation(),event.getEventTime(),dayWeekText); }
			return handleNotificationApproval(ntf, adminIds, tempIds);			
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
		if(ntfToBeUpdated.getTargetType().equalsIgnoreCase("event")) {
			ntfToBeUpdated.setUpdatedBy(username);
			
			ntfToBeUpdated.setTargetTeamId(ntfDb.getTargetTeamId(ntfToBeUpdated.getUuid(),userService.getUserByEmailId(username).get().getId()));
			
		}
		ntfToBeUpdated.setAction(status);
		ntfToBeUpdated.setUpdatedBy(username);
		ntfToBeUpdated.setUpdatedTime(new Date());
		NotificationApproval updatedNotification = ntfApprovalDb.save(ntfToBeUpdated);
		Notification newNtf = new Notification();
		newNtf.setMessage("Your previous request has been "+ updatedNotification.getAction()+"("+updatedNotification.getMessage()+")");
		newNtf.setBroadcastType("single");		
		newNtf.setTargetType("user");
		newNtf.setTargetId(userService.getUserByEmailId(updatedNotification.getCreatedBy()).get().getId()); //No value fetched for update/edit ntfs
		newNtf.setMappingTableData(updatedNotification.getMappingTableData());
		newNtf.setUpdatedBy(updatedNotification.getUpdatedBy()); 
		newNtf.setUpdatedTime(new Date());
		newNtf.setCreatedBy(updatedNotification.getCreatedBy());
		newNtf.setCreatedTime(new Date());
		newNtf.setUuid(UUID.randomUUID());
		newNtf.setTitle("Status Update");
		List<Integer> userIds = new ArrayList<Integer>();
		userIds.add(newNtf.getTargetId());
		if(!this.handleNotification(newNtf, userIds))
			System.out.println("Couldnt send message");
		return updatedNotification;
	}

	
	public List<NotificationUi> getntf(String username, String createdDate)
	{
		List<NotificationUi> result = ntfApprovalDb.findByUserName( username );
		List<NotificationUi> finalRes = new ArrayList<>();
		for(NotificationUi res : result) {
			Date todayDate = new Date();
			String currentDate = new SimpleDateFormat("yyyy/MM/dd").format(todayDate);  
			String createdTime =new SimpleDateFormat("yyyy/MM/dd").format(res.getCreatedTime());
			if(createdDate.equalsIgnoreCase("Today") && res.getAction().equalsIgnoreCase("waiting")) {
				System.out.println(currentDate);
				System.out.println(createdTime);

				if(currentDate.equalsIgnoreCase(createdTime))
				finalRes.add(res);
			}
			//	else {
//				finalRes.add(res);
//			}
		}
		return finalRes;
	}

	//cancel invite
	public boolean saveNotificationCancel(Notification ntf) {
		ntf.setTitle("Kirtan Cancel Invite");
		Team team = teamService.getTeamById(ntfDb.getTeamLeadId(ntf.getTargetId()));
		List<Integer> adminIds = new ArrayList<>();
		adminIds.add(userService.getUserByEmailId(team.getTeamLeadId()).get().getId());
		return handleNotification(ntf, adminIds);
	}
}

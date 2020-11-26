package org.iskon.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.iskon.models.Notification;
import org.iskon.models.NotificationApproval;
import org.iskon.models.NotificationTracker;
import org.iskon.models.NotificationUi;
import org.iskon.models.User;
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
	
	public	List<NotificationUi> getAll(String username) {
		List<NotificationUi> result = ntfApprovalDb.findByUserName( username );
		result.addAll(ntfDb.findByUserName(username));
		return result;
	}

	public NotificationUi getOne(String ntfId, String userName) {
		NotificationUi ntf = ntfDb.findByUuid(ntfId,userName);
		NotificationUi ntfAppr = ntfApprovalDb.findByUuid(ntfId,userName);
		if(ntf == null) 
			if(ntfAppr == null)
				return null;
			else
				 return ntfAppr;
		else
			 return ntf;
	}

	
	private Boolean handleNotification(Notification ntf,List<Integer> userIds) {	
		try {
			Notification savedNtf = ntfDb.save(ntf);
			Map<String,String> ntfData= new HashMap<>();
			for(int userId : userIds) {
				NotificationTracker ntfTracker = new NotificationTracker(userId, ntf.getTargetId(),
						savedNtf.getUuid().toString());
				List<String> token = userTokenDb.findDeviceTokenByUserId(userId);
				if (token == null) {
					System.out.println("No token found for the given userId! Thus no notification can be sent to"
							+ "the user.");
					ntfTracker.setStatus(false);
				}
				else{
					System.out.println(userId+token.toString());
					FireBaseMessagingService fcm = new FireBaseMessagingService();
					ntfData.put("title", savedNtf.getTitle());
					ntfData.put("message",savedNtf.getMessage());
					Boolean flag = false;
					String response="";
					for(String s : token) {
						if( s!=null)
							response = fcm.sendToUser(ntfData, s);
						if(response != null) flag = true; 
						else {
							userTokenDb.deleteDeviceTokenById(userId,s);
						}
					}
					ntfTracker.setStatus(flag);
				}
				ntfTrackerRepo.save(ntfTracker);
			}
			return true;
		}
		catch(Exception err) {
			System.out.println("Error at HandleNotification"+err.getMessage());
			err.printStackTrace();;
			return false;
		}
		
	}

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
					response = fcm.sendToUser(ntfData, s);
					if(response != null) flag = true; 
				}
				ntfTracker.setStatus(flag);
			}
			ntfTrackerRepo.save(ntfTracker);
		}
		 return true;
	}

		public Boolean saveNotification(Notification ntf) {
			if (ntf.getBroadcastType().equalsIgnoreCase("single")) {
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
				if (tableToQuery.equalsIgnoreCase("event")) {
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
						return handleNotification(ntf, userId);
					} else if(tableToQuery.equalsIgnoreCase("user_temple")){
						ntf.setTitle("Kirtan Admin Updates");					
						List<Integer> adminIds = new ArrayList<>();
						adminIds.addAll(ntfDb.getAdminId());
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

	
	public Boolean saveNotificationAppr(NotificationApproval ntf) {
		/*if (ntf.getBroadcastType().toLowerCase().equalsIgnoreCase("single")) {
			ntf.setTitle("Kirtan Admin Updates");
			List<Integer> userIds = new ArrayList<Integer>();
			ntf.setAction("waiting");
			userIds.add(ntf.getTargetId());
			return handleNotificationApproval(ntf, userIds);
		} else if (ntf.getBroadcastType().equalsIgnoreCase("multiple")) {
		*/	ntf.setTitle("Kirtan Admin Updates");
			ntf.setAction("waiting");
			List<Integer> adminIds = ntfDb.getAdminId();
			return handleNotificationApproval(ntf, adminIds);			
		/*} else
			return false;*/
	}

	public NotificationApproval updateApproval(String status,String ntfId,String username) {
		NotificationApproval ntfToBeUpdated = ntfApprovalDb.findNotificationApprovalByUuid(ntfId); 
		if(ntfToBeUpdated == null) {
			System.out.println("Notification to be updated does not exist");
			return null;
		}
		ntfToBeUpdated.setAction(status);
		ntfToBeUpdated.setUpdatedBy(username);
		ntfToBeUpdated.setUpdatedTime(new Date());
		NotificationApproval updatedNotification = ntfApprovalDb.save(ntfToBeUpdated);
		Notification newNtf = new Notification();
		newNtf.setMessage("Your previous request has been "+ updatedNotification.getAction()+"("+updatedNotification.getMessage()+")");
		newNtf.setBroadcastType("single");		
		newNtf.setTargetType("user");
		newNtf.setTargetId(userService.getUserByEmailId(updatedNotification.getCreatedBy()).get().getId());
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
}

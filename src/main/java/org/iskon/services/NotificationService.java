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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {
	@Autowired
	private NotificationJpaRepository ntfDb;
	@Autowired
	private NotificationApprovalJpaRepository ntfApprovalDb;

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
				String token = userService.getUserById(userId).getDeviceToken();
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
					String response = fcm.sendToUser(ntfData, token);
					if(response != null) ntfTracker.setStatus(true); 
					else ntfTracker.setStatus(false);;
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
		for(int userId : userIds) {
			String token = userService.getUserById(userId).getDeviceToken();
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
				String response = fcm.sendToUser(ntfData, token);
				if(response != null) ntfTracker.setStatus(true); 
				else ntfTracker.setStatus(false);
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
			String tableToQuery = ntf.getMappingTableData();
				if(tableToQuery == "") {
					System.out.println("Required Mapping data");
					return false;
				}
				if (tableToQuery.equalsIgnoreCase("event_user")) {
					ntf.setTitle("Kirtan Event Updates");
					List<Integer> userId = ntfDb.getParticipants(ntf.getTargetId());
					System.out.println(userId);
					return handleNotification(ntf, userId);
				} else if (tableToQuery.equalsIgnoreCase("team_user")) {
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
		if (ntf.getBroadcastType().toLowerCase().equalsIgnoreCase("single")) {
			ntf.setTitle("Kirtan Admin Updates");
			List<Integer> userIds = new ArrayList<Integer>();
			ntf.setAction("waiting");
			userIds.add(ntf.getTargetId());
			return handleNotificationApproval(ntf, userIds);
		} else if (ntf.getBroadcastType().equalsIgnoreCase("multiple")) {
			ntf.setTitle("Kirtan Admin Updates");
			ntf.setAction("waiting");
			List<Integer> adminIds = ntfDb.getAdminId();
			return handleNotificationApproval(ntf, adminIds);			
		} else
			return false;
	}

	@Transactional
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
		newNtf.setMessage("Your previous request has been "+ updatedNotification.getAction()+" by admin :"+ updatedNotification.getTargetId());
		newNtf.setTargetId(updatedNotification.getTargetId());
		newNtf.setTargetType(updatedNotification.getTargetType());
		newNtf.setBroadcastType(updatedNotification.getBroadcastType());		
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

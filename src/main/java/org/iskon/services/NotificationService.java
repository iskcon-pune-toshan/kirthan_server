package org.iskon.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.iskon.models.Notification;
import org.iskon.models.NotificationApproval;
import org.iskon.models.NotificationTracker;
import org.iskon.repositories.NotificationApprovalJpaRepository;
import org.iskon.repositories.NotificationJpaRepository;
import org.iskon.repositories.NotificationTrackerJpaRepository;
import org.iskon.repositories.UserTokenJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {
	enum STATUS{APPROVED,REJECTED,WAITING};

	@Autowired
	private NotificationJpaRepository ntfDb;
	@Autowired
	private NotificationApprovalJpaRepository ntfApprovalDb;
	@Autowired
	private UserTokenJpaRepository userTokenDb;
	@Autowired
	NotificationTrackerJpaRepository ntfTrackerRepo;

	public	Map<String,Object> getAll(int userId) {
		Map<String,Object> result = new HashMap<>();
		result.put("ntf",  ntfDb.findByUserId(userId));
		result.put("ntf_appr",ntfApprovalDb.findByUserId(userId));
		return result;
	}

	public Map<String,Object> getOne(String ntfId, int userId) {
		Notification ntf = ntfDb.findByNotificationId(ntfId);
		NotificationApproval ntfAppr = ntfApprovalDb.findByUuid(ntfId);
		Map<String,Object> result = new HashMap<>();
		//System.out.println(ntf);
		//System.out.println(ntfAppr);
		
		if(ntf == null) 
			if(ntfAppr == null)
				return null;
			else
				 result.put("ntf_appr", ntfAppr);
		else
			 result.put("ntf",ntf);
	//	System.out.println("called");
	//	System.out.println(result);
		return result;
	}


	private Boolean handleNotification(Notification ntf,List<Integer> userIds) {	
		try {
			Notification savedNtf = ntfDb.save(ntf);
			Map<String,String> ntfData= new HashMap<>();
			//System.out.println(userIds);
			for(int userId : userIds) {
				NotificationTracker ntfTracker = new NotificationTracker(savedNtf.getTargetId(), userId, savedNtf.getUuid().toString());
				String token = userTokenDb.findDeviceTokenByUserId(userId);
				if (token == null) {
					System.out.println("No token found for the given userId! Thus no notification can be sent to the user.");
					ntfTracker.setStatus(false);
				}
				else{
					FireBaseMessagingService fcm = new FireBaseMessagingService();
					ntfData.put("title", savedNtf.getTitle());
					ntfData.put("message",savedNtf.getMessage());
					String response = fcm.sendToUser(ntfData, token);
					if(response == null)
						ntfTracker.setStatus(false);
					else
						ntfTracker.setStatus(true);
				}
				ntfTrackerRepo.save(ntfTracker);
			}
			return true;
		}
		catch(Exception err) {
			System.out.println("Error at HandleNotification(50)"+err.getMessage());
			return false;
		}
		
	}

	private Boolean handleNotificationApproval(NotificationApproval ntf,List<Integer> userIds ) {
		NotificationApproval savedNtf = ntfApprovalDb.save(ntf);
		for(int userId : userIds) {
			String token = userTokenDb.findDeviceTokenByUserId(userId);
			NotificationTracker ntfTracker = new NotificationTracker(savedNtf.getTargetId(), userId, savedNtf.getUuid().toString());
			
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
				if(response == null)
					ntfTracker.setStatus(false);
				else ntfTracker.setStatus(true);
			}
			ntfTrackerRepo.save(ntfTracker);
		}
		 return true;
	}

		public Boolean saveNotification(Notification ntf) {
			if (ntf.getBroadcastType().equalsIgnoreCase("single")) {
				List<Integer> ids = new ArrayList<>();
				ids.add(ntf.getTargetId());
				//System.out.println(ids);
				return handleNotification(ntf,ids);
			}
			else if (ntf.getTargetType().equals("multiple")) {
			String tableToQuery = ntf.getMappingTableData();
				if(tableToQuery == "") {
					System.out.println("Required Mapping data");
					return false;
				}
				if (tableToQuery.equalsIgnoreCase("event_user_mapping")) {
					ntf.setTitle("Kirtan Event Updates");
					List<Integer> userId = ntfDb.getParticipants(ntf.getTargetId());
					return handleNotification(ntf, userId);
					//System.out.println("Method not written 116");
					//return true;

				} else if (tableToQuery.equalsIgnoreCase("team_user_mapping")) {
						ntf.setTitle("Kirtan Team Updates");
						ArrayList<Integer> teamId = new ArrayList<>();
						teamId.add(ntf.getTargetId());
						List<Integer> userId = ntfDb.getTeamMemberId(teamId);
						return handleNotification(ntf, userId);
						//System.out.println("Method not written 123");
						//	return true;

					} else if(tableToQuery.equalsIgnoreCase("temple_user_mapping")){
						System.out.println("Method not written 125");
						//currently coded to work on city will need to later find out the admins
						ntf.setTitle("Kirtan Admin Updates");
						//return true;
						List<Integer> adminIds = new ArrayList<>();
						//	List<Integer> adminIds = ntfDb.getLocalAdminId(ntf.getTargetId());
						//List<Integer> adminIds = ntfDb.getLocalAdminId(ntf.getCreatedBy());
						return handleNotification(ntf,adminIds);
		
					}else{
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
			List<Integer> userIds = new ArrayList<Integer>();
			ntf.setAction("waiting");
			userIds.add(ntf.getTargetId());
			System.out.println(ntf.toString());
			return handleNotificationApproval(ntf, userIds);
		} else if (ntf.getBroadcastType().equalsIgnoreCase("multiple")) {
			String tableToQuery = ntf.getMappingTableData();
			ntf.setTitle("Kirtan Admin Updates");
			//List<Integer> adminIds = ntfDb.getLocalAdminId(ntf.getCreatedBy());
			return true;
			//return handleNotificationApproval(ntf,adminIds);
		} else
			return false;
	}

	@Transactional
	public NotificationApproval updateApproval(String status,String ntfId,int userId) {

		NotificationApproval ntfToBeUpdated = ntfApprovalDb.findByUuid(ntfId);
		ntfToBeUpdated.setAction(status);
		ntfToBeUpdated.setUpdatedBy(userId);
		ntfToBeUpdated.setUpdatedTime(new Date());
		NotificationApproval updatedNotification = ntfApprovalDb.save(ntfToBeUpdated);
		//System.out.println(updatedNotification);
		Notification newNtf = new Notification();
		newNtf.setMessage("Your previous request has been "+ updatedNotification.getAction()+" by admin :"+ updatedNotification.getTargetId());
		newNtf.setTargetType(updatedNotification.getTargetType());
		newNtf.setBroadcastType("single");
		newNtf.setTargetId(updatedNotification.getCreatedBy());
		newNtf.setMappingTableData("none");
		newNtf.setUpdatedBy(updatedNotification.getUpdatedBy());
		newNtf.setUpdatedTime(new Date());
		newNtf.setCreatedBy(updatedNotification.getUpdatedBy());
		newNtf.setCreatedTime(new Date());
		//System.out.println(newNtf);
		newNtf.setUuid(UUID.randomUUID());
		newNtf.setTitle("Status Update");
		List<Integer> userIds = new ArrayList<Integer>();
		userIds.add(newNtf.getTargetId());
		if(!this.handleNotification(newNtf, userIds))
			System.out.println("Couldnt send message");
		return updatedNotification;
		
	
	}
}

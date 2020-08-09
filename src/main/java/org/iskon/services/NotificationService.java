package org.iskon.services;

import org.iskon.models.NotificationApprovalModel;
import org.iskon.models.NotificationModel;
import org.iskon.models.NotificationTrackerModel;
import org.iskon.repositories.NotificationRepository;
import org.iskon.repositories.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository ntfDb;

	@Autowired
	private UserTokenRepository userTokenDb;

	public Map<String,Object> getAll(int userId) {
		return ntfDb.getAll(userId);
	}

	public Map<String,Object> getOne(String ntfId, int userId) {
		return ntfDb.getNotificationById(ntfId, userId);
	}

	private Boolean handleNotification(NotificationModel ntf,
			String tableName,List<Integer> userIds) {
		System.out.println(userIds);
		for(int userId : userIds) {
			String token = userTokenDb.fetchDeviceToken(userId);			
			NotificationTrackerModel ntfTracker = new NotificationTrackerModel(
					ntf.getTargetId(), userId, ntf.getId());
			if (token == null) {
				System.out.println("No token found for the given userId! Thus no notification can be sent to the user.");
				ntfTracker.setStatus(false);
			}
			else {
				Map<String, String> data = new HashMap<>();
				FireBaseMessagingService fcm = new FireBaseMessagingService();
				Map<String,Object> ntfData = new HashMap<>();
				ntfData.put("title",ntf.getTitle());
				ntfData.put("message",ntf.getMessage());
				String response = fcm.sendToUser(data, ntfData, token);
				ntfTracker.setStatus(response != null);
			}
			ntfDb.saveNotificationTracker(ntfTracker);
		}
		return ntfDb.save(ntf, tableName);
	}
	
	private Boolean handleNotification(NotificationApprovalModel ntf,
			String tableName,List<Integer> userIds) {
		System.out.println(userIds);
		for(int userId : userIds) {
			String token = userTokenDb.fetchDeviceToken(userId);			
			NotificationTrackerModel ntfTracker = new NotificationTrackerModel(
					ntf.getTargetId(), userId, ntf.getId());
			if (token == null) {
				System.out.println("No token found for the given userId! Thus no notification can be sent to the user.");
				ntfTracker.setStatus(false);
			}
			else {
				Map<String, String> data = new HashMap<>();
				FireBaseMessagingService fcm = new FireBaseMessagingService();
				Map<String,Object> ntfData = new HashMap<>();
				ntfData.put("title",ntf.getTitle());
				ntfData.put("message",ntf.getMessage());
				String response = fcm.sendToUser(data, ntfData, token);
				ntfTracker.setStatus(response != null);
			}
			ntfDb.saveNotificationTracker(ntfTracker);
		}
		return ntfDb.save(ntf, tableName);
	}

	public Boolean saveNotification(NotificationModel ntf,
			Map<String, Object> metaData) {
		String tableName = "notifications";
		// Notification to a single user
		if (metaData.get("targetType").toString().toLowerCase()
				.equals("single")) {
			List<Integer> ids = new ArrayList<>();
			ids.add(ntf.getTargetId());
			return handleNotification(ntf, tableName,ids);
		}
		else if (metaData.get("targetType").equals("multiple")) {
			String tableToQuery = ntf.getMappingTable();
			if(tableToQuery == "") {
				System.out.println("Required Mapping data");
				return false;
			}
			if (tableToQuery.equalsIgnoreCase("event_user_mapping")) {
			
				ntf.setTitle("Kirtan Event Updates");
				List<Integer> userId = ntfDb.getParticipants(ntf.getTargetId());
				return handleNotification(ntf, tableName, userId);
			
			} else if (tableToQuery.equalsIgnoreCase("team_user_mapping")) {
				
				ntf.setTitle("Kirtan Team Updates");
				ArrayList<Integer> teamId = new ArrayList<>();
				teamId.add(ntf.getTargetId());
				List<Integer> userId = ntfDb.getTeamMemberId(teamId);
				return handleNotification(ntf, tableName, userId);
			
			} else if(tableToQuery.equalsIgnoreCase("temple_user_mapping")){
				
				//currently coded to work on city will need to later find out the admins
				ntf.setTitle("Kirtan Admin Updates");
				List<Integer> adminIds = ntfDb.getTempleAdminId(ntf.getTargetId());
				return handleNotification(ntf,tableName,adminIds);
			
			}else{
				System.out.println("Incorrect mapping");
				return false;
			}
		} else {
			System.out.println("Invalid target option");
			return false;
		}
	}

	public Boolean saveNotificationApproval(NotificationApprovalModel ntf,
			Map<String, Object> metaData) {
		String tableName = "notification_approval";
		if (metaData.get("targetType").toString().equalsIgnoreCase("single")) {
			System.out.println("Here");
			List<Integer> userIds = new ArrayList<Integer>();
			userIds.add(ntf.getTargetId());
			return handleNotification(ntf, tableName, userIds);		
		} else if (metaData.get("targetType").toString().toLowerCase()
				.equals("multiple")) {
			String tableToQuery = ntf.getMappingTable();
			ntf.setTitle("Kirtan Admin Updates");
			List<Integer> adminIds = ntfDb.getTempleAdminId(ntf.getTargetId());
			return handleNotification(ntf,tableName,adminIds);
		} else
			return false;
	}

	// next to be implemented
	public Boolean updateApproval(Map<String, Object> metaData) {
		String tableName = "notification_approval";
		NotificationApprovalModel updatedNtf= ntfDb.updateNotificationById(metaData);
		if(updatedNtf == null) {
			System.out.println("Error Updating notifications");
			return false;
		}
		Map<String,Object> newNtf = new HashMap<>();
		newNtf.put("message","Your previous request has been "+ updatedNtf.getAction()+" by admin :"+metaData.get("userId"));
		newNtf.put("type",updatedNtf.getType());
		newNtf.put("targetId",updatedNtf.getCreatorId());
		metaData.put("targetType","Single");
		newNtf.put("userId",metaData.get("userId"));
		newNtf.put("mappingTableData","none");
		System.out.println(newNtf);
		NotificationModel ntf = new NotificationModel(newNtf);
		List<Integer> userIds = new ArrayList<Integer>();
		userIds.add(ntf.getTargetId());
		return this.handleNotification(ntf, "notifications", userIds);
	}
}

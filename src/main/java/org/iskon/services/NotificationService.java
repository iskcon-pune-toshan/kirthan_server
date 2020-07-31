package org.iskon.services;

import org.iskon.models.NotificationListModel;
import org.iskon.models.NotificationModel;
import org.iskon.repositories.NotificationListRepository;
import org.iskon.repositories.NotificationRepository;
import org.iskon.repositories.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {
	
	@Autowired 
	private NotificationRepository ntfDb;
	@Autowired
	private NotificationListRepository ntfListDb;
	@Autowired
	private UserTokenRepository userTokenDb;
	
	public List<NotificationModel> getAll(int userId){
		return ntfDb.getAll(userId);		
	}
	
	public NotificationModel getOne(String ntfId,int userId){
		return ntfDb.getNotificationById(ntfId,userId);
	}
		
	public Boolean save(NotificationModel ntf , Map<String,Object> metaData) {
		String tableName;
		if(ntf.getType().equals("info")) {
			 tableName = "notifications";
		}
		else {
			tableName = "notification_approval";
		}
		
		if(ntfDb.save(ntf,tableName) == null) {
			return false;
		}		
		else {
			if(metaData.get("targetType").toString().toLowerCase().equals("single")) {
				String token = userTokenDb.fetchDeviceToken((int) metaData.get("targetId"));
				sendNotification(ntf,token);//this returns an id which can be used as a notificationId;
			}
			else if(metaData.get("targetType").toString().toLowerCase().equals("multiple")){
				List<NotificationListModel> users = ntfListDb.userList(ntf.getTargetId());
				if(users == null)
					System.out.println("err");
				else {
					String tokenList = userTokenDb.fetchDeviceToken((int) users.get(0).getUserId());
					sendNotification(ntf,tokenList);
				}	
			}
			return true;
		}
	}
	
	
	// next to be implemented
	public Boolean update(NotificationModel ntf ,Map<String,Object> metaData) {
			//Map<String,Object> newData = new HashMap<>();
			NotificationModel oldData = ntfDb.getNotificationById(ntf.getId(), 12);
			if(ntf.getId() == oldData.getId() && oldData.getType() != "info"){
				String response =(String) metaData.get("responseStatus");	
				ntfDb.save(ntf, "notification_approval");
				return true;
			}
			else {
				return false;
			}
		}
	
	private Boolean sendNotification(
				NotificationModel ntfData,
				String token){
			Map<String,String> data = new HashMap<>();
			FireBaseMessagingService fcm = new FireBaseMessagingService();
			fcm.sendToUser(data,ntfData,token);	
			return false;
	}		
}

package org.iskon.repositories;

import org.iskon.models.NotificationApprovalModel;
import org.iskon.models.NotificationModel;
import org.iskon.models.NotificationTrackerModel;
import java.util.List;
import java.util.Map;

//Generic Question:Should i add different files for these following implementation

public interface NotificationRepository {


//Notifications table	
	public Boolean save(NotificationModel ntf, String tableName);

//Notification Approval table 

	public Boolean save(NotificationApprovalModel ntf, String tableName);
	public NotificationApprovalModel updateNotificationById(Map<String, Object> body);// works

//shared functions
	public Map<String,Object> getAll(int userId);// @Requires userId
	public Map<String,Object> getNotificationById(String ntfId, int userId); // works
	public List<Integer> getParticipants(int eventId);
	public List<Integer> getTeamMemberId(List<Integer> teamId);
	public List<Integer> getTempleAdminId(int templeId);
	public Boolean saveNotificationTracker(NotificationTrackerModel trackerDetails);

}

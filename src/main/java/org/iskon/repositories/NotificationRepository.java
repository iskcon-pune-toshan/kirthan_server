package org.iskon.repositories;

import org.iskon.models.NotificationListModel;
import org.iskon.models.NotificationModel;
import org.iskon.models.UserTokenModel;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

//Generic Question:Should i add different files for these following implementation

public interface NotificationRepository {
//user level notifications
	public List<NotificationModel> getAll(int userId);// @Requires userId

	public Boolean save(NotificationModel ntf, String tableName);

//Notification level 
	public NotificationModel getNotificationById(String ntfId, int userId); // works

	public Boolean updateNotificationById(Map<String, Object> body,
			String tableName);// works




}

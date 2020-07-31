package org.iskon.repositories;

import java.util.List;
import java.util.Map;

import org.iskon.models.NotificationListModel;

public interface NotificationListRepository {

	public List<NotificationListModel> userList(int eventId);
	public Boolean addUser(NotificationListModel userDetails);
	public Boolean removeUser(Map<String,Object> body);

}

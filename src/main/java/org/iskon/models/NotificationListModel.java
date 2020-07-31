package org.iskon.models;

import java.util.Map;

public class NotificationListModel {
	
	@KeyField
	private int id;
	
	private int userId;
	private int groupId;
	private String userType;
	
	public NotificationListModel(int userId,int groupId,String userType) {
		this.userId = userId;
		this.groupId = groupId;
		this.userType = userType;
	}
	
	public NotificationListModel(int id,Map<String,Object> data) {
		this.groupId =(int) data.get("groupId");
		this.id = id;
		this.userId = (int)data.get("userId");
		this.userType = (String)data.get("userType");
	}
	
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int id) {
		this.id = id;
	}
	
	public int getGroupId() {
		return this.groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = id ;
	}
	
	public String getUserType() {
		return this.userType;
	}
	public void setUserType(String type) {
		this.userType = type ;
	}
}


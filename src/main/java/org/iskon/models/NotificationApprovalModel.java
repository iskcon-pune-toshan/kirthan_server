package org.iskon.models;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class NotificationApprovalModel {
	
	
	@KeyField
	private String title;
	
	private UUID id;
	private String type;
	private String message;
	private int createdBy;
	private LocalDateTime createdAt;
	private int targetId;
	@UpdateAllowed
	private String action;
	private String mappingTableData;
	

	//creating a new notification_appr
	public NotificationApprovalModel(Map<String,Object> data) {
		this.message =(String) data.get("message");
		this.type = (String)data.get("type");
		this.id = UUID.randomUUID();
		this.createdAt = LocalDateTime.now();
		this.createdBy =  (int) (data.get("userId"));
		this.targetId = (int) data.get("targetId");
		this.action = "WAIT";
		this.mappingTableData = (String) data.get("mappingTableData");
	}
	
	//reading an existing notification
	public NotificationApprovalModel(UUID id,Map<String,Object> ntf) {
		this.id = id;
		this.message =(String) ntf.get("message");
		this.type =(String) ntf.get("type");
		this.createdAt = (LocalDateTime) ntf.get("createdAt");
		this.createdBy = (int) ntf.get("createdBy");
		this.targetId = (int) ntf.get("targetId");
		this.action = (String) ntf.get("action");
		this.mappingTableData = (String) ntf.get("mappingTableData");
	}
	
	public String getId() {
		return this.id.toString();
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMappingTable() {
		return this.mappingTableData;
	}
	
	public String getType() {
		return this.type;	
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public int getTargetId() {
		return this.targetId;
	}
	
	public int getCreatorId() {
		return this.createdBy;
	}
	
	public LocalDateTime getTimeOfCreation() {
		return this.createdAt;
	}
	
	public void setMessage(String data) {
		this.message = data;
	}
	public String getAction() {
		return this.action;
	}
	public void setAction(Boolean val) {
		if(val) this.action = "Approved";
		else this.action = "Rejected";
	}
	public void setId(String senderId) {
		System.out.println(senderId);
	}
}

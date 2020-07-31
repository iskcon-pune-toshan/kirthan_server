package org.iskon.models;
	
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationModel {
	
	private UUID id;
	private String message;
	private String type;
	@KeyField
	private String body; //to be implemented
	private int createdBy;
	private LocalDateTime createdAt;
	private int targetId;
	
	public NotificationModel(@JsonProperty("ntfId") String id,
			@JsonProperty("notification") String message,
			@JsonProperty("type") String type) {
		this.id = UUID.fromString(id);
		this.message =(String) message;
		this.type = type;
	}
	
	public NotificationModel(String message,String type) {
		this.message = message;
		this.type = type;
		this.id = UUID.randomUUID();
		this.createdAt = LocalDateTime.now();	
	}
	//creating a new notification
	public NotificationModel(Map<String,Object> data) {
		this.message =(String) data.get("message");
		this.type = (String)data.get("type");
		this.id = UUID.randomUUID();
		this.createdAt = LocalDateTime.now();
		this.createdBy =  Integer.parseInt((String)data.get("userId"));
		this.targetId = (int) data.get("targetId");
	}
	//reading an existing notification
	public NotificationModel(UUID id,Map<String,Object> ntf) {
		this.id = id;
		this.message =(String) ntf.get("message");
		this.type =(String) ntf.get("type");
		this.createdAt = (LocalDateTime) ntf.get("createdAt");
		this.createdBy = (int) ntf.get("createdBy");
		this.targetId = (int) ntf.get("targetId");
	}
	
	public String getId() {
		return this.id.toString();
	}
	
	public String getTitle() {
		return this.message;
	}
	
	public String getType() {
		return this.type;	
	}
	
	public String getBody() {
		return this.body;
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

	public void setBody(String data) {
		this.body = data;
	}
}

package org.iskon.models;

import java.util.Date;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class NotificationUi {

	private String uuid;
	
	private String message;

	private String targetType;
	
	private int targetId;
	
	private String createdBy;
	
	private Date createdTime;
	
	private String updatedBy;
	
	private Date updatedTime;
	
	private int id;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public int getId() {
		return id;
	}

	private String action;

	public NotificationUi(String uuid, String message, String targetType, int targetId, String createdBy,
			Date createdTime, String updatedBy, Date updatedTime, String action, int id) {
		super();
		this.uuid = uuid;
		this.message = message;
		this.targetType = targetType;
		this.targetId = targetId;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
		this.action = action;
		this.id = id;
	}

	public NotificationUi(String uuid, String message, String targetType, int targetId, String createdBy,
			Date createdTime, String updatedBy, Date updatedTime, int id) {
		super();
		this.uuid = uuid;
		this.message = message;
		this.targetType = targetType;
		this.targetId = targetId;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
		this.action = null;
		this.id = id;
	}
	
}

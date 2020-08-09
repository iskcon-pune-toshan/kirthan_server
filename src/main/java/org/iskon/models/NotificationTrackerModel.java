package org.iskon.models;

import java.util.UUID;

public class NotificationTrackerModel {
	@KeyField
	private int id;
	
	private int targetId;
	private int userId;
	private String ntfId;
	private String status;
	
	
	public NotificationTrackerModel(
			int targetId,
			int userId,
			String ntfId) {
	this.targetId = targetId;
	this.userId = userId;
	this.ntfId = ntfId;
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return this.userId;
	}
	
	public int getTargetId() {
		return this.targetId;
	}
	
	public String getNtfId() {
		return this.ntfId;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(Boolean val) {
		if(val) status="sent";
		else status = "notSent";
	}
	
}

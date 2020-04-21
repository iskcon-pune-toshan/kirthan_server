package org.iskon.models;

import java.util.Date;

public class EventTeamUserMapping extends BaseModel {
	
	@KeyField
	Integer id;
	Integer eventId;
	Integer teamId;
	Integer userId;
	
	@UpdateAllowed
	String approvalStatus;
	@UpdateAllowed
	String approvalComments;

	
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getApprovalComments() {
		return approvalComments;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public void setApprovalComments(String approvalComments) {
		this.approvalComments = approvalComments;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}

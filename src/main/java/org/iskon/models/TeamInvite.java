package org.iskon.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity	
@Table(name="team_invite")
public class TeamInvite implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "team_id")
	private Integer teamId;

	@Column(name = "notification_id")
	private String notificationId;

	@Column(name = "is_processed")
	private Boolean isProcessed;
	
	@Column(name = "event_id")
	private Integer eventId;
	
	private TeamInvite(){

	}
	public TeamInvite(Integer id, Integer eventId, String notificationId, Integer teamId, Boolean isProcessed) {
		this.id = id;
		this.eventId = eventId;
		this.notificationId = notificationId;
		this.teamId = teamId;
		this.isProcessed = isProcessed;
	}
	
	public TeamInvite(Integer eventId, String notificationId, Integer teamId, Boolean isProcessed) {
		this.eventId = eventId;
		this.notificationId = notificationId;
		this.teamId = teamId;
		this.isProcessed = isProcessed;
	}

	public Integer getId() {
		return id;
	}

	public Integer getEventId() {
		return eventId;
	}
	public Integer getTeamId() {
		return teamId;
	}
	
	public String getNotificationId() {
		return notificationId;
	}

	public Boolean getIsProcessed() {
		return isProcessed;
	}

	public void setTeamId(Integer tid) {
		this.teamId = tid;
	}

	public void setIsProcessed(Boolean isProcessed) { 
		this.isProcessed = isProcessed;
	}

	public void setEventId(Integer eventId) { 
		this.eventId = eventId;
	}
}

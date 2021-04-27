package org.iskon.models;

import javax.persistence.*;

@Entity	
@Table(name="notification_tracker")
public class NotificationTracker {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "target_id")
	private int targetId;

	@Column(name = "notification_id")
	private String notificationId;

	@Column(name = "status")
	private String status;
	
	@Column(name = "target_team_id")
	private Integer targetTeamId;
	

	public NotificationTracker(int userId, int targetId, String notificationId, Integer targetTeamId) {
		this.userId = userId;
		this.targetId = targetId;
		this.notificationId = notificationId;
		this.targetTeamId = targetTeamId;
	}

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public int getTargetId() {
		return targetId;
	}
	
	public Integer getTargetTeamId() {
		return targetTeamId;
	}
	
	public void setTargetId(Integer tid) {
		this.targetTeamId = tid;
	}

	public String getNotificationId() {
		return notificationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(Boolean val) {
		if(val) status="sent";
		else status = "notSent";
	}


	
}

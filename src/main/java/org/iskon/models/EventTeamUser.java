package org.iskon.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "event_team_user")
public class EventTeamUser implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(name = "event_id")
	Integer eventId;

	@Column(name = "team_id")
	Integer teamId;

	@Column(name = "user_id")
	Integer userId;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "created_time")
	private Date createdTime;

	@Column(name = "updated_time")
	private Date updatedTime;

	@Transient
	private String eventTitle;

	@Transient
	private String teamName;

	@Transient
	private String userName;

	private EventTeamUser() {

	}

	private EventTeamUser(Integer id, Integer eventId, Integer teamId, Integer userId, String createdBy,
			String updatedBy, Date createdTime, Date updatedTime) {
		this.id = id;
		this.eventId = eventId;
		this.teamId = teamId;
		this.userId = userId;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public EventTeamUser(Integer id, Integer eventId, Integer teamId, Integer userId, String createdBy,
			String updatedBy, Date createdTime, Date updatedTime, String teamName, String userName, String eventTitle) {
		this.id = id;
		this.eventId = eventId;
		this.teamId = teamId;
		this.userId = userId;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.eventTitle = eventTitle;
		this.teamName = teamName;
		this.userName = userName;
	}

	public static EventTeamUser buildEventTeamUser(Integer id, Integer eventId, Integer teamId, Integer userId,
			String createdBy, String updatedBy, Date createdTime, Date updatedTime) {
		return new EventTeamUser(id, eventId, teamId, userId, createdBy, updatedBy, createdTime, updatedTime);
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

	public Integer getUserId() {
		return userId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getUserName() {
		return userName;
	}
	
	
}

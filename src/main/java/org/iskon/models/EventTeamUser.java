package org.iskon.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="event_user_mapping")
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

	public static EventTeamUser buildEventTeamUser(Integer id, Integer eventId, Integer teamId, Integer userId,
												   String createdBy, String updatedBy, Date createdTime,
												   Date updatedTime) {
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
}



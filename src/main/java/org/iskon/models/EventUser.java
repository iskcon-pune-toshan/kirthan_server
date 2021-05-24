package org.iskon.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "event_user")
public class EventUser implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(name = "event_id")
	Integer eventId;

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
	private String eventName;

	@Column(name = "user_name")
	private String userName;

	private EventUser() {

	}


	public EventUser(Integer id, Integer eventId, Integer userId, String createdBy,
			String updatedBy, Date createdTime, Date updatedTime, String userName) {
		this.id = id;
		this.eventId = eventId;
//		this.teamId = teamId;
		this.userId = userId;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.userName = userName;
	}

	public static EventUser buildEventTeamUser(Integer id, Integer eventId, Integer userId,
			String createdBy, String updatedBy, Date createdTime, Date updatedTime, String userName) {
		return new EventUser(id, eventId, userId, createdBy, updatedBy, createdTime, updatedTime, userName);
	}

	public Integer getId() {
		return id;
	}

	public Integer getEventId() {
		return eventId;
	}

//	public Integer getTeamId() {
//		return teamId;
//	}

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


	public String getEventName() {
		return eventName;
	}

//	public String getTeamName() {
//		return teamName;
//	}

	public String getUserName() {
		return userName;
	}
	
	
}

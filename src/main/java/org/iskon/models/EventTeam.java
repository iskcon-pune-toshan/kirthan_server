package org.iskon.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="event_team")
public class EventTeam implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "event_id")
	Integer eventId;

	@Column(name = "team_id")
	Integer teamId;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "created_time")
	private Date createdTime;

	@Column(name = "updated_time")
	private Date updatedTime;
	
	@Column(name = "team_name")
	private String teamName;
	
	@Transient
	private String eventName;


	public EventTeam(){

	}

	private EventTeam(Integer id, Integer eventId, Integer teamId, String createdBy, String updatedBy,
					 Date createdTime, Date updatedTime, String teamName) {
		this.id = id;
	this.eventId = eventId;
		this.teamId = teamId;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.teamName = teamName;

	}

	public EventTeam(Integer id, Integer eventId, Integer teamId, String createdBy, String updatedBy,
					 Date createdTime, Date updatedTime, String teamName, String eventName ) {
		this.id = id;
		this.eventId = eventId;
		this.teamId = teamId;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.teamName = teamName;
		this.eventName = eventName;

	}

	public static EventTeam buildTeamUser(Integer id, Integer eventId, Integer teamId, String createdBy, String updatedBy,
										 Date createdTime, Date updatedTime, String teamName) {
		return new EventTeam(id, eventId, teamId, createdBy, updatedBy, createdTime, updatedTime, teamName);
	}

	public Integer getId() {
		return id;
	}

	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(int eid) {
		this.eventId = eid;
	}

	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(int tid) {
		this.teamId = tid;
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

	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String name) {
		this.teamName = name;
	}

	public String getEventName() {
		return eventName;
	}


}

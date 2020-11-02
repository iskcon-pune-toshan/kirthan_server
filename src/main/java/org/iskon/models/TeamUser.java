package org.iskon.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="team_user")
public class TeamUser implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Integer id;

	@Column(name = "user_id")
	Integer userId;

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
	
	@Transient
	private String teamName;
	
	@Transient
	private String userName;


	private TeamUser(){

	}

	private TeamUser(Integer id, Integer userId, Integer teamId, String createdBy, String updatedBy,
					 Date createdTime, Date updatedTime) {
		this.id = id;
		this.userId = userId;
		this.teamId = teamId;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;

	}

	public TeamUser(Integer id, Integer userId, Integer teamId, String createdBy, String updatedBy,
					 Date createdTime, Date updatedTime, String teamName, String userName ) {
		this.id = id;
		this.userId = userId;
		this.teamId = teamId;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.teamName = teamName;
		this.userName = userName;

	}

	public static TeamUser buildTeamUser(Integer id, Integer userId, Integer teamId, String createdBy, String updatedBy,
										 Date createdTime, Date updatedTime) {
		return new TeamUser(id, userId, teamId, createdBy, updatedBy, createdTime, updatedTime);
	}

	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getTeamId() {
		return teamId;
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

	public String getUserName() {
		return userName;
	}


}

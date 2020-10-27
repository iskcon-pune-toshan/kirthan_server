package org.iskon.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="team")
public class Team implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "team_title")
	private String teamTitle;

	@Column(name = "team_description")
	private String teamDescription;

	@Column(name = "is_processed")
	private Boolean isProcessed;

	@Column(name = "approval_status")
	private String approvalStatus;

	@Column(name = "approval_comments")
	private String approvalComments;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "created_time")
	private Date createdTime;

	@Column(name = "updated_time")
	private Date updatedTime;

	private Team(){

	}

	private Team(Integer id, String teamTitle, String teamDescription, Boolean isProcessed, String approvalStatus,
				String approvalComments, String createdBy, String updatedBy, Date createdTime,
				 Date updatedTime) {
		this.id = id;
		this.teamTitle = teamTitle;
		this.teamDescription = teamDescription;
		this.isProcessed = isProcessed;
		this.approvalStatus = approvalStatus;
		this.approvalComments = approvalComments;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public static Team buildTeam(Integer id, String teamTitle, String teamDescription, Boolean isProcessed,
								 String approvalStatus, String approvalComments, String createdBy, String updatedBy,
								 Date createdTime, Date updatedTime) {
		return new Team(id, teamTitle, teamDescription, isProcessed, approvalStatus, approvalComments, createdBy,
				updatedBy, createdTime, updatedTime);
	}

	public Integer getId() {
		return id;
	}

	public String getTeamTitle() {
		return teamTitle;
	}

	public String getTeamDescription() {
		return teamDescription;
	}

	public Boolean getProcessed() {
		return isProcessed;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public String getApprovalComments() {
		return approvalComments;
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
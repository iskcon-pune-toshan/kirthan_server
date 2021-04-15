package org.iskon.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="team")
public class Team implements Serializable {

	public Boolean getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public void setApprovalComments(String approvalComments) {
		this.approvalComments = approvalComments;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "team_lead")
	private Integer teamLead;
	
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
	
//	private Team(Integer id, String teamLead, String teamTitle, String teamDescription, Boolean isProcessed, String approvalStatus,
//			String approvalComments, String createdBy, String updatedBy, Date createdTime,
//			 Date updatedTime) {
//	this.id = id;
//	this.teamLead = null;
//	this.teamTitle = teamTitle;
//	this.teamDescription = teamDescription;
//	this.isProcessed = isProcessed;
//	this.approvalStatus = approvalStatus;
//	this.approvalComments = approvalComments;
//	this.createdBy = createdBy;
//	this.updatedBy = updatedBy;
//	this.createdTime = createdTime;
//	this.updatedTime = updatedTime;
//}

	public Team(Integer id, Integer teamLead, String teamTitle, String teamDescription, Boolean isProcessed, String approvalStatus,
				String approvalComments, String createdBy, String updatedBy, Date createdTime,
				 Date updatedTime) {
		this.id = id;
		this.teamLead = teamLead;
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

	public static Team buildTeam(Integer id, Integer teamLead, String teamTitle, String teamDescription, Boolean isProcessed,
								 String approvalStatus, String approvalComments, String createdBy, String updatedBy,
								 Date createdTime, Date updatedTime) {
		return new Team(id, teamLead, teamTitle, teamDescription, isProcessed, approvalStatus, approvalComments, createdBy,
				updatedBy, createdTime, updatedTime);
	}

	public Integer getId() {
		return id;
	}
	
	public Integer getTeamLead() {
		return teamLead;
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
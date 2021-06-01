package org.iskon.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="team")
public class Team implements Serializable {

//	public Boolean getIsProcessed() {
//		return isProcessed;
//	}
//
//	public void setIsProcessed(Boolean isProcessed) {
//		this.isProcessed = isProcessed;
//	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

//	public void setApprovalComments(String approvalComments) {
//		this.approvalComments = approvalComments;
//	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "team_title")
	private String teamTitle;

	@Column(name = "team_description")
	private String teamDescription;
	
//	@Column(name = "available_from")
//	private String availableFrom;
//	
//	@Column(name = "available_to")
//	private String availableTo;
//	
//	@Column(name = "weekday")
//	private String weekDay;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "category")
	private Integer category;
	
	@Column(name = "experience")
	private String experience;
	
	@Column(name = "phone_number")
	private Long phoneNumber;
	
	@Column(name = "team_lead_id")
	private String teamLeadId;

//	@Column(name = "is_processed")
//	private Boolean isProcessed;

	@Column(name = "approval_status")
	private String approvalStatus;

//	@Column(name = "approval_comments")
//	private String approvalComments;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "created_time")
	private Date createdTime;

	@Column(name = "updated_time")
	private Date updatedTime;
	
	@Column(name = "local_admin_area")
	private String localAdminArea;

	@Column(name = "local_admin_name")
	private String localAdminName;
	
	@Column(name = "request_acceptance")
	private Integer requestAcceptance;
	
	@Column(name = "duration")
	private Integer duration;
	
	@Transient
	private List<TeamUser> listOfTeamMembers;
	
	private Team(){

	}

	private Team(Integer id, String teamTitle, String teamDescription,String location,Integer category,String experience,Long phoneNumber,String teamLeadId, String approvalStatus,
				 String createdBy, String updatedBy, Date createdTime,
				 Date updatedTime,String localAdminArea, String localAdminName, List<TeamUser> listOfTeamMembers, Integer requestAcceptance, Integer duration) {
		this.id = id;
		this.teamTitle = teamTitle;
		this.teamDescription = teamDescription;
//		this.availableFrom = availableFrom;
//		this.availableTo = availableTo;
//		this.weekDay = weekDay;
		this.location = location;
		this.category = category;
		this.experience = experience;
		this.phoneNumber = phoneNumber;
		this.teamLeadId = teamLeadId;
//		this.isProcessed = isProcessed;
		this.approvalStatus = approvalStatus;
//		this.approvalComments = approvalComments;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.localAdminArea = localAdminArea;
		this.localAdminName = localAdminName;
		this.listOfTeamMembers = listOfTeamMembers;
		this.requestAcceptance = requestAcceptance;
		this.duration = duration;
	}

	public static Team buildTeam(Integer id, String teamTitle, String teamDescription,
								 String approvalStatus,String createdBy, String updatedBy,
								 Date createdTime, Date updatedTime, 
								 String location,
								 Integer category,
								 String experience,
								 Long phoneNumber,
								 String teamLeadId,String localAdminArea, String localAdminName, List<TeamUser> listOfTeamMembers, Integer requestAcceptance, Integer duration
) {
		return new Team(id, teamTitle, teamDescription,location,category,experience,phoneNumber,teamLeadId, approvalStatus, createdBy,
				updatedBy, createdTime, updatedTime,localAdminArea,localAdminName, listOfTeamMembers, requestAcceptance, duration);
	}

	public Integer getId() {
		return id;
	}

	public String getTeamTitle() {
		return teamTitle;
	}

	
//	public String getAvailableFrom() {
//		return availableFrom;
//	}
//	
//	public String getAvailableTo() {
//		return availableTo;
//	}
//	public String getWeekDay() {
//		return weekDay;
//	}
	
	public String getLocation() {
		return location;
	}
	
	public Integer getCategory() {
		return category;
	}
	
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getExperience() {
		return experience;
	}
	
	public String getTeamLeadId() {
		return teamLeadId;
	}
	
	public String getTeamDescription() {
		return teamDescription;
	}

//	public Boolean getProcessed() {
//		return isProcessed;
//	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

//	public String getApprovalComments() {
//		return approvalComments;
//	}

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
	
	public String getLocalAdminArea() {
		return localAdminArea;
	}
	
	public String getLocalAdminName() {
		return localAdminName;
	}
	
	public List<TeamUser> getListOfTeamMembers(){
		return listOfTeamMembers;
	}
	
	public Integer getRequestAcceptance() {
		return requestAcceptance;
	}
	
	public Integer getDuration() {
		return duration;
	}
}
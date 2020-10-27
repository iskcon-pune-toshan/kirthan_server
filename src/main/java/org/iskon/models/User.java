package org.iskon.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="user")
public class User implements Serializable {

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

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "phone_number")
	private Long phoneNumber;

	@Column(name = "add_line_one")
	private String addLineOne;

	@Column(name = "add_line_two")
	private String addLineTwo;

	@Column(name = "add_line_three")
	private String addLineThree;

	@Column(name = "locality")
	private String locality;

	@Column(name = "city")
	private String city;

	@Column(name = "pincode")
	private Integer pinCode;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Column(name = "govt_id_type")
	String govtIdType;

	@Column(name = "govt_id")
	String govtId;

	@Column(name = "is_processed")
	private Boolean isProcessed;

	@Column(name = "approval_status")
	private String approvalStatus;

	@Column(name = "approval_comments")
	private String approvalComments;

	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "created_time")
	private Date createdTime;

	@Column(name = "updated_time")
	private Date updatedTime;

	private User(){

	}

	private User(Integer id, String firstName, String lastName, String email, String userName, String password,
				 Long phoneNumber, String addLineOne, String addLineTwo, String addLineThree, String locality,
				 String city, Integer pinCode, String state, String country, String govtIdType, String govtId,
				 Boolean isProcessed, String approvalStatus, String approvalComments, Integer roleId,
				 String createdBy, String updatedBy, Date createdTime, Date updatedTime) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.addLineOne = addLineOne;
		this.addLineTwo = addLineTwo;
		this.addLineThree = addLineThree;
		this.locality = locality;
		this.city = city;
		this.pinCode = pinCode;
		this.state = state;
		this.country = country;
		this.govtIdType = govtIdType;
		this.govtId = govtId;
		this.isProcessed = isProcessed;
		this.approvalStatus = approvalStatus;
		this.approvalComments = approvalComments;
		this.roleId = roleId;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public static User buildUser(Integer id, String firstName, String lastName, String email, String userName,
								 String password, Long phoneNumber, String addLineOne, String addLineTwo,
								 String addLineThree, String locality, String city, Integer pinCode, String state,
								 String country, String govtIdType, String govtId, Boolean isProcessed,
								 String approvalStatus, String approvalComments, Integer roleId,
								 String createdBy, String updatedBy, Date createdTime, Date updatedTime) {
		return new User(id, firstName, lastName, email, userName, password, phoneNumber, addLineOne, addLineTwo,
				addLineThree, locality, city, pinCode, state, country, govtIdType, govtId, isProcessed,
				approvalStatus, approvalComments, roleId, createdBy, updatedBy, createdTime, updatedTime);
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddLineOne() {
		return addLineOne;
	}

	public String getAddLineTwo() {
		return addLineTwo;
	}

	public String getAddLineThree() {
		return addLineThree;
	}

	public String getLocality() {
		return locality;
	}

	public String getCity() {
		return city;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getGovtIdType() {
		return govtIdType;
	}

	public String getGovtId() {
		return govtId;
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

	public Integer getRoleId() {
		return roleId;
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

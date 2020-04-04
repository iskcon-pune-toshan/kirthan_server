package org.iskon.models;


public class UserRequest extends BaseModel {
//public class UserRequest  {

	String firstName;
	String lastName;
	String email;
	String userName;
	String password;
	Long phoneNumber;
	String addLineOne;
	String addLineTwo;
	String addLineThree;
	String locality;
	String city;
	Integer pinCode;
	String state;
	String country;
	String govtIdType;
	String govtId;
	Boolean isProcessed;
	Integer id;
	String approvalStatus;
	String approvalComments;
	
	String userId;
	String userType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Boolean getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddLineOne() {
		return addLineOne;
	}

	public void setAddLineOne(String addLineOne) {
		this.addLineOne = addLineOne;
	}

	public String getAddLineTwo() {
		return addLineTwo;
	}

	public void setAddLineTwo(String addLineTwo) {
		this.addLineTwo = addLineTwo;
	}

	public String getAddLineThree() {
		return addLineThree;
	}

	public void setAddLineThree(String addLineThree) {
		this.addLineThree = addLineThree;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGovtIdType() {
		return govtIdType;
	}

	public void setGovtIdType(String govtIdType) {
		this.govtIdType = govtIdType;
	}

	public String getGovtId() {
		return govtId;
	}

	public void setGovtId(String govtId) {
		this.govtId = govtId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApprovalComments() {
		return approvalComments;
	}

	public void setApprovalComments(String approvalComments) {
		this.approvalComments = approvalComments;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String status) {
		this.approvalStatus = status;
	}

	/*
	 * @Override public String toString() { // TODO Auto-generated method stub
	 * return getJson(); }
	 * 
	 * public String getJson() { ObjectMapper mapper = new ObjectMapper();
	 * mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); try {
	 * return mapper.writeValueAsString(this); } catch (JsonProcessingException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } return null; }
	 */
}

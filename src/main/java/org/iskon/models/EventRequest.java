package org.iskon.models;

import java.util.Date;

public class EventRequest extends BaseModel {
	
	@KeyField
	Integer eventId;
	String eventTitle;
	String eventDescription;
	Date eventDate;
	String eventDuration;
	String eventLocation;
	String eventType;
	Long phoneNumber;
	String addLineOne;
	String addLineTwo;
	String addLineThree;
	String locality;
	String city;
	Integer pinCode;
	String state;
	String country;
	Boolean isProcessed;
	String approvalStatus;
	String approvalComments;
	
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventDuration() {
		return eventDuration;
	}
	public void setEventDuration(String eventDuration) {
		this.eventDuration = eventDuration;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
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
	public Boolean getIsProcessed() {
		return isProcessed;
	}
	public void setIsProcessed(Boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getApprovalComments() {
		return approvalComments;
	}
	public void setApprovalComments(String approvalComments) {
		this.approvalComments = approvalComments;
	}
	
	
	
}

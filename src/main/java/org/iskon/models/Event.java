package org.iskon.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="event")
public class Event implements Serializable {

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

	@Column(name = "event_title")
	private String eventTitle;

	@Column(name = "event_description")
	private String eventDescription;

	@Column(name = "event_date")
	private Date eventDate;

	@Column(name = "event_duration")
	private String eventDuration;

	@Column(name = "event_location")
	private String eventLocation;

	@Column(name = "event_type")
	private String eventType;

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
	private Integer pincode;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

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
	
	@Column(name = "source_longitude")
	private Double sourceLongitude;
	
	@Column(name = "source_latitude")
	private Double sourceLatitude;
	
	@Column(name = "destination_longitude")
	private Double destinationLongitude;
	
	@Column(name = "destination_latitude")
	private Double destinationLatitude;
	
	@Column(name = "event_mobility")
	private String eventMobility;

	private Event(){

	}

	private Event(Integer id, String eventTitle, String eventDescription, Date eventDate, String eventDuration,
				  String eventLocation, String eventType, Long phoneNumber, String addLineOne, String addLineTwo,
				  String addLineThree, String locality, String city, Integer pincode, String state, String country,
				  Boolean isProcessed, String approvalStatus, String approvalComments,
				  String createdBy, String updatedBy, Date createdTime, Date updatedTime, Double sourceLongitude,
				  Double sourceLatitude, Double destinationLongitude, Double destinationLatitude, String eventMobility ) {
		this.id = id;
		this.eventTitle = eventTitle;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.eventDuration = eventDuration;
		this.eventLocation = eventLocation;
		this.eventType = eventType;
		this.phoneNumber = phoneNumber;
		this.addLineOne = addLineOne;
		this.addLineTwo = addLineTwo;
		this.addLineThree = addLineThree;
		this.locality = locality;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
		this.country = country;
		this.isProcessed = isProcessed;
		this.approvalStatus = approvalStatus;
		this.approvalComments = approvalComments;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.sourceLongitude = sourceLongitude;
		this.sourceLatitude = sourceLatitude;
		this.destinationLongitude = destinationLongitude;
		this.destinationLatitude = destinationLatitude;
		this.eventMobility = eventMobility;
	}

	public static Event buildEvent(Integer id, String eventTitle, String eventDescription, Date eventDate,
								   String eventDuration, String eventLocation, String eventType, Long phoneNumber,
								   String addLineOne, String addLineTwo, String addLineThree, String locality,
								   String city, Integer pincode, String state, String country, Boolean isProcessed,
								   String approvalStatus, String approvalComments,
								   String createdBy, String updatedBy, Date createdTime, Date updatedTime, 
								   Double sourceLongitude, Double sourceLatitude, Double destinationLongitude,
								   Double destinationLatitude, String eventMobility) {
		return new Event(id, eventTitle, eventDescription, eventDate,eventDuration, eventLocation, eventType,
				phoneNumber, addLineOne, addLineTwo, addLineThree, locality,city, pincode, state, country,
				isProcessed, approvalStatus, approvalComments, createdBy, updatedBy, createdTime, updatedTime, 
				sourceLongitude, sourceLatitude, destinationLongitude, destinationLatitude, eventMobility);
	}

	public Integer getId() {
		return id;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public String getEventDuration() {
		return eventDuration;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public String getEventType() {
		return eventType;
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

	public Integer getPincode() {
		return pincode;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public Boolean getIsProcessed() {
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
	
	public Double getSourceLongitude() {
		return sourceLongitude;
	}

	public Double getSourceLatitude() {
		return sourceLatitude;
	}

	public Double getDestinationLongitude() {
		return destinationLongitude;
	}

	public Double getDestinationLatitude() {
		return destinationLatitude;
	}

	public String getEventMobility() {
		return eventMobility;
	}

	
	
}

package org.iskon.models;

import javax.persistence.*;

import com.google.api.client.util.DateTime;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="event")
public class Event implements Serializable {
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setIsProcessed(Boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	public void setIsPublicEvent(Boolean isPublicEvent) {
		this.isPublicEvent = isPublicEvent;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public void setApprovalComments(String approvalComments) {
		this.approvalComments = approvalComments;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", eventTitle=" + eventTitle + ", eventDescription=" + eventDescription
				+ ", eventDate=" + eventDate + ", eventDuration=" + eventDuration + ", eventLocation=" + eventLocation
				+ ", eventType=" + eventType + ", phoneNumber=" + phoneNumber + ", addLineOneS=" + addLineOneS
				+ ", addLineTwoS=" + addLineTwoS + ", localityS=" + localityS + ", addLineOneD=" + addLineOneD
				+ ", addLineTwoD=" + addLineTwoD + ", localityD=" + localityD + ", city="+ city + ", pincode=" + pincode + ", state=" + state + ", country=" + country + ", isProcessed="
				+ isProcessed + ", approvalStatus=" + approvalStatus + ", approvalComments=" + approvalComments
				+ ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + ", sourceLongitude=" + sourceLongitude + ", sourceLatitude="
				+ sourceLatitude + ", destinationLongitude=" + destinationLongitude + ", destinationLatitude="
				+ destinationLatitude + ", eventMobility=" + eventMobility + ", isPubicEvent=" + isPublicEvent + ", status=" + status + ", cancelReason=" + cancelReason + "]";
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

	@Column(name = "add_line_one_s")
	private String addLineOneS;

	@Column(name = "add_line_two_s")
	private String addLineTwoS;

	@Column(name = "locality_s")
	private String localityS;

	@Column(name = "add_line_one_d")
	private String addLineOneD;

	@Column(name = "add_line_two_d")
	private String addLineTwoD;

	@Column(name = "locality_d")
	private String localityD;

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
	
	@Column(name = "event_time")
	private String eventTime;
	
	@Column(name = "public_event")
	private Boolean isPublicEvent;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "cancel_reason")
	private String cancelReason;

	private Event(){

	}

	private Event(Integer id, String eventTitle, String eventDescription, Date eventDate, String eventDuration,
				  String eventLocation, String eventType, Long phoneNumber, String addLineOneS, String addLineTwoS,
				  String localityS, String addLineOneD, String addLineTwoD,
				  String localityD,String city, Integer pincode, String state, String country,
				  Boolean isProcessed, String approvalStatus, String approvalComments,
				  String createdBy, String updatedBy, Date createdTime, Date updatedTime, Double sourceLongitude,
				  Double sourceLatitude, Double destinationLongitude, Double destinationLatitude, String eventMobility,
				  String eventTime, Boolean isPublicEvent, Integer status, String cancelReason ) {
		this.id = id;
		this.eventTitle = eventTitle;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.eventDuration = eventDuration;
		this.eventLocation = eventLocation;
		this.eventType = eventType;
		this.phoneNumber = phoneNumber;
		this.addLineOneS = addLineOneS;
		this.addLineTwoS = addLineTwoS;
		this.localityS = localityS;
		this.addLineOneD = addLineOneD;
		this.addLineTwoD = addLineTwoD;
		this.localityD = localityD;
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
		this.eventTime = eventTime;
		this.isPublicEvent = isPublicEvent;
		this.status = status;
		this.cancelReason = cancelReason;
	}

	public static Event buildEvent(Integer id, String eventTitle, String eventDescription, Date eventDate,
								   String eventDuration, String eventLocation, String eventType, Long phoneNumber,
								   String addLineOneS, String addLineTwoS, String localityS,
								   String addLineOneD, String addLineTwoD, String localityD,
								   String city, Integer pincode, String state, String country, Boolean isProcessed,
								   String approvalStatus, String approvalComments,
								   String createdBy, String updatedBy, Date createdTime, Date updatedTime, 
								   Double sourceLongitude, Double sourceLatitude, Double destinationLongitude,
								   Double destinationLatitude, String eventMobility, String eventTime, Boolean isPublicEvent,
								   Integer status, String cancelReason) {
		return new Event(id, eventTitle, eventDescription, eventDate,eventDuration, eventLocation, eventType,
				phoneNumber, addLineOneS, addLineTwoS, localityS, addLineOneD, addLineTwoD, localityD,city, pincode, state, country,
				isProcessed, approvalStatus, approvalComments, createdBy, updatedBy, createdTime, updatedTime, 
				sourceLongitude, sourceLatitude, destinationLongitude, destinationLatitude, eventMobility, eventTime, isPublicEvent,
				status, cancelReason);
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

	public String getAddLineOneS() {
		return addLineOneS;
	}

	public String getAddLineTwoS() {
		return addLineTwoS;
	}

	public String getLocalityS() {
		return localityS;
	}
	
	public String getAddLineOneD() {
		return addLineOneD;
	}

	public String getAddLineTwoD() {
		return addLineTwoD;
	}

	public String getLocalityD() {
		return localityD;
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
	
	public String getEventTime() {
		return eventTime;
	}

	public Boolean getIsPublicEvent() {
		return isPublicEvent;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public String getCancelReason() {
		return cancelReason;
	}
	
	
}
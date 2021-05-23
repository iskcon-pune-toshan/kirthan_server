package org.iskon.models;

import javax.persistence.*;

import com.google.api.client.util.DateTime;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="event")
public class Event implements Serializable {
	
	public void setStatus(Integer status) {
		this.teamInviteStatus = status;
	}

	public void setIsPublicEvent(Boolean isPublicEvent) {
		this.isPublicEvent = isPublicEvent;
	}
	

	@Override
	public String toString() {
		return "Event [id=" + id + ", eventTitle=" + eventTitle + ", eventDescription=" + eventDescription
				+ ", eventDate=" + eventDate + ", eventEndTime=" + eventEndTime
				+ ", eventType=" + eventType + ", phoneNumber=" + phoneNumber + ", addLineOneS=" + addLineOneS
				+ ", addLineTwoS=" + addLineTwoS + ", localityS=" + localityS + ", addLineOneD=" + addLineOneD
				+ ", addLineTwoD=" + addLineTwoD + ", localityD=" + localityD + ", city="+ city + ", pincode=" + pincode
				+ ", state=" + state + ", country=" + country
				+ ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + ", longitudeS=" + longitudeS + ", latitudeS="
				+ latitudeS + ", longitudeD=" + longitudeD + ", latitudeD="
				+ latitudeD + ", eventMobility=" + eventMobility + ", isPubicEvent=" + isPublicEvent 
				+ ", teamInviteStatus=" + teamInviteStatus + ", cancelReason=" + cancelReason + ", serviceType=" + serviceType + "]";
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
	
	@Column(name = "event_start_time")
	private String eventStartTime;
	
	@Column(name = "event_end_time")
	private String eventEndTime;

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

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "created_time")
	private Date createdTime;

	@Column(name = "updated_time")
	private Date updatedTime;
	
	@Column(name = "longitude_s")
	private Double longitudeS;
	
	@Column(name = "latitude_s")
	private Double latitudeS;
	
	@Column(name = "longitude_d")
	private Double longitudeD;
	
	@Column(name = "latitude_d")
	private Double latitudeD;
	
	@Column(name = "event_mobility")
	private String eventMobility;
	
	@Column(name = "public_event")
	private Boolean isPublicEvent;
	
	@Column(name = "team_invite_status")
	private Integer teamInviteStatus;
	
	@Column(name = "cancel_reason")
	private String cancelReason;
	
	@Column(name = "service_type")
	private String serviceType;

	private Event(){

	}

	private Event(Integer id, String eventTitle, String eventDescription, Date eventDate,String eventStartTime ,String eventEndTime ,
				  String eventType, Long phoneNumber, String addLineOneS, String addLineTwoS,
				  String localityS, String addLineOneD, String addLineTwoD,
				  String localityD,String city, Integer pincode, String state, String country,
				  String createdBy, String updatedBy, Date createdTime, Date updatedTime, Double longitudeS,
				  Double latitudeS, Double longitudeD, Double latitudeD, String eventMobility,
				  Boolean isPublicEvent, Integer teamInviteStatus, String cancelReason, String serviceType ) {
		this.id = id;
		this.eventTitle = eventTitle;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.eventStartTime = eventStartTime;
		this.eventEndTime = eventEndTime;
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
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.longitudeS = longitudeS;
		this.latitudeS = latitudeS;
		this.longitudeS = longitudeS;
		this.latitudeD = latitudeD;
		this.eventMobility = eventMobility;
		this.isPublicEvent = isPublicEvent;
		this.teamInviteStatus = teamInviteStatus;
		this.cancelReason = cancelReason;
		this.serviceType = serviceType;
	}

	public static Event buildEvent(Integer id, String eventTitle, String eventDescription, Date eventDate,
								   String eventStartTime, String eventEndTime, String eventType, Long phoneNumber,
								   String addLineOneS, String addLineTwoS, String localityS,
								   String addLineOneD, String addLineTwoD, String localityD,
								   String city, Integer pincode, String state, String country,
								   String createdBy, String updatedBy, Date createdTime, Date updatedTime, 
								   Double longitudeS, Double latitudeS, Double longitudeD,
								   Double latitudeD, String eventMobility, Boolean isPublicEvent,
								   Integer teamInviteStatus, String cancelReason, String serviceType) {
		return new Event(id, eventTitle, eventDescription, eventDate,eventStartTime, eventEndTime, eventType,
				phoneNumber, addLineOneS, addLineTwoS, localityS, addLineOneD, addLineTwoD, localityD,city, pincode, state, country,
				 createdBy, updatedBy, createdTime, updatedTime, 
				longitudeS, latitudeS, longitudeD, latitudeD, eventMobility, isPublicEvent,
				teamInviteStatus, cancelReason, serviceType);
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

	public String getEventStartTime() {
		return eventStartTime;
	}

	public String getEventEndTime() {
		return eventEndTime;
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
	
	public Double getLongitudeS() {
		return longitudeS;
	}

	public Double getLatitudeS() {
		return latitudeS;
	}

	public Double getLongitudeD() {
		return longitudeD;
	}

	public Double getLatitudeD() {
		return latitudeD;
	}

	public String getEventMobility() {
		return eventMobility;
	}
	

	public Boolean getIsPublicEvent() {
		return isPublicEvent;
	}
	
	public Integer getTeamInviteStatus() {
		return teamInviteStatus;
	}
	
	public String getCancelReason() {
		return cancelReason;
	}
	
	public String getServiceType() {
		return serviceType;
	}
	
}
package org.iskon.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="preferences")
public class Preferences implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_id")
	private String userid;
	
	@Column(name = "area")
	private String area;
	
	@Column(name = "local_admin")
	private String localAdmin;


	@Column(name = "event_duration")
	private Integer eventDuration;
	
	@Column(name = "request_acceptance")
	private String requestAcceptance;
	
	@Column(name = "interested_event_id")
	private String interestedEventId;
	
	@Override
	public String toString() {
		return "Event [id=" + id + ", userid=" + userid + ", area=" + area
				+ ", localAdmin=" + localAdmin + ", eventDuration=" + eventDuration + ", requestAcceptance=" + requestAcceptance + ", interestedEventId=" + interestedEventId + "]";
	}

	public Preferences() {
		
	}


	public Preferences(Integer id, String userid, String area, String localAdmin, Integer eventDuration,
				  String requestAcceptance, String interestedEventId ) {
		this.id = id;
		this.userid = userid;
		this.area = area;
		this.localAdmin = localAdmin;
		this.eventDuration = eventDuration;
		this.requestAcceptance = requestAcceptance;
		this.interestedEventId = interestedEventId;
	}

//	public Preferences(Integer id, String userid, String area, String localAdmin, Integer eventDuration,
//			  String requestAcceptance ) {
//	this.id = id;
//	this.userid = userid;
//	this.area = area;
//	this.localAdmin = localAdmin;
//	this.eventDuration = eventDuration;
//	this.requestAcceptance = requestAcceptance;
//}
	
	public static Preferences buildPreferences(Integer id, String userid,String area, String localAdmin, Integer eventDuration,
			  String requestAcceptance, String interestedEventId) {
		return new Preferences(id,userid, area, localAdmin, eventDuration, requestAcceptance, interestedEventId);
	}

	public Integer getId() {
		return id;
	}
	
	public String getUserId() {
		return userid;
	}

	public String getArea() {
		return area;
	}

	public String getLocalAdmin() {
		return localAdmin;
	}
	public Integer getEventDuration() {
		return eventDuration;
	}

	public String getRequestAcceptance() {
		return requestAcceptance;
	}
	
	public String getInterestedEventId(){
		return interestedEventId;
	}

//	public void setUserId(String userid) {
//		this.userid = userid;
//	}

	
	
}

package org.iskon.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class EventSearch implements Serializable {

    private Date eventStartDate;
    
    private Date eventDate;

    private Date eventEndDate;

    private Integer eventDuration;

    private List<String> eventLocation;

    private List<String> eventType;

    private List<String> locality;

    private List<String> city;

    private Integer pincode;

    private List<String> state;

    private Boolean isProcessed;
    
    private String dateInterval;
    
    private List<String> createdBy;
    
    private String approvalStatus;

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }
    
    public Date getEventDate() {
        return eventDate;
    }

    public Integer getEventDuration() {
        return eventDuration;
    }

    public List<String> getEventLocation() {
        return eventLocation;
    }

    public List<String> getEventType() {
        return eventType;
    }

    public List<String> getLocality() {
        return locality;
    }

    public List<String> getCity() {
        return city;
    }

    public Integer getPincode() {
        return pincode;
    }

    public List<String> getState() {
        return state;
    }

    public Boolean getIsProcessed() {
        return isProcessed;
    }

    public List<String> getCreatedBy() {
        return createdBy;
    }
    
    public String getDateInterval(){
    	return dateInterval;
    }
    
    public void setEventEndDate(Date eventEndDate) {
    	this.eventEndDate = eventEndDate;
    }

	public String getApprovalStatus() {
		return approvalStatus;
	}
}

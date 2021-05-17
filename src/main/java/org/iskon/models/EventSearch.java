package org.iskon.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class EventSearch implements Serializable {
	private Integer id;

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
    
    private Boolean isPublicEvent;
    
    private Integer status;
    
    private List<String> cancelReason;
    
    private String serviceType;

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
    public Integer getId() {
    	return id;
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
	
	public Boolean getIsPublicEvent() {
        return isPublicEvent;
    }
	
	public List<String> getCancelReason(){
		return cancelReason;
	}
	
	public Integer getStatus() {
		return status;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
}
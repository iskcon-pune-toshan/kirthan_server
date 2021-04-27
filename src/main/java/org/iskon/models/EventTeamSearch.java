package org.iskon.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EventTeamSearch implements Serializable {

    
    private List<String> createdBy;
    
   private Integer id;
   private Integer eventId;

    public List<String> getCreatedBy() {
        return createdBy;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setEventId(Integer id) {
    	this.eventId = id;
    }
    
    public Integer getEventId() {
        return eventId;
    }
}

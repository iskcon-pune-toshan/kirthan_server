package org.iskon.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EventTeamUserSearch implements Serializable {

   private Integer id;
   private Integer teamId;
   private Integer eventId;
   private Integer userId;
   	
   public Integer getId() {
	   return id;
   }
   public Integer getTeamId() {
	   return teamId;
   }
   public Integer getEventId() {
	   return eventId;
   }
   public Integer getUserId() {
	   return userId;
   }
    
}

package org.iskon.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EventUserSearch implements Serializable {

   private Integer id;
   private Integer teamId;
   private Integer eventId;
   private Integer userId;
   private String userName;
   	
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
public String getUserName() {
	return userName;
}

public void setUserName(String username) {
	this.userName = userName;
}
    
}

package org.iskon.models;
import java.util.List;
import java.io.Serializable;

public class PreferencesSearch implements Serializable {
	
	 private Integer eventDuration;
	 private List<String> area;
	 private List<String> localAdmin;
	 private List<String> requestAcceptance;
	 private String userid;

	 public Integer getEventDuration(){
	    	return eventDuration;
	    }
	 
	 public List<String> getArea(){
	    	return area;
	    }

	 public List<String> getLocalAdmin(){
	    	return localAdmin;
	    }
	 public List<String> getRequestAcceptance(){
	    	return requestAcceptance;
	    }

	public String getUserId() {
		return userid;
	}
	 
//	 public void setUserId(Integer uid){
//	    	this.userId = uid;
//	    }
}

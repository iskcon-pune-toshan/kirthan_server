package org.iskon.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserSearch implements Serializable {

    private Boolean isProcessed;
    private Integer id;
    private List<String> firstName;
    private List<String> lastName;
    private Integer roleId;

    public List<String> getFirstName(){
    	return firstName;
    }
    public List<String> getLastName(){
    	return lastName;
    }
    public Integer getId() {
    	return id;
    }
    public Boolean getIsProcessed() {
        return isProcessed;
    }
    
    public Integer getRoleId() {
    	return roleId;
    }

  
}

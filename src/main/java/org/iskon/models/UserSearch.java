package org.iskon.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Expression;

public class UserSearch implements Serializable {

    private Boolean isProcessed;
    private Integer id;
    private List<String> firstName;
    private List<String> lastName;
    private Integer roleId;
    private String approvalStatus;
    private String email;
    private Integer invitedBy;

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
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public String getEmail() {
		return email;
	}
	public Integer getInvitedBy() {
		return invitedBy;
	}
  
}

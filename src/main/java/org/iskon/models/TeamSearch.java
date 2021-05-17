package org.iskon.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TeamSearch implements Serializable {

    private Boolean isProcessed;
    private Integer id;
    private List<String> createdBy;
    private String approvalStatus;
    private String teamLeadId;
    private String localAdminName;
    
    private Date createTime;

    public Date getCreatedTime() {
        return createTime;
    }
    public Integer getId() {
    	return id;
    }
    public Boolean getIsProcessed() {
        return isProcessed;
    }

    public List<String> getCreatedBy() {
        return createdBy;
    }
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public String getTeamLeadId() {
		return teamLeadId;
	}
	public void setTeamLeadId(String teamLeadId) {
		this.teamLeadId = teamLeadId;
	}
	public String getLocalAdminName() {
		return localAdminName;
	}
	public void setLocalAdminName(String localAdminName) {
		this.localAdminName = localAdminName;
	}
}

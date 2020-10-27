package org.iskon.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TeamUserSearch implements Serializable {

    private Integer id;
    private Integer teamId;
    private List<String> createdBy;
    
   

    public Integer getTeamId() {
    	return teamId;
    }


    public Integer getId() {
    	return id;
    }


    public List<String> getCreatedBy() {
        return createdBy;
    }
}

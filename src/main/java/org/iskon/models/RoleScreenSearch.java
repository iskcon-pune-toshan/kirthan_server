package org.iskon.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RoleScreenSearch implements Serializable {

    
    private Integer id;
    private Integer roleId;
    private Integer screenId;

    public Integer getId() {
        return id;
    }

    public Integer getRoleId() {
        return roleId;
    }
    
    public Integer getScreenId() {
        return screenId;
    }
  
    
}

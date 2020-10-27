package org.iskon.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserTempleSearch implements Serializable {

    
    private Integer id;

    private Integer roleId;
    
    private Integer templeId;
    
    private Integer userId;
    

    public Integer getId() {
        return id;
    }
    
    public Integer getRoleId() {
        return roleId;
    }
    
    public Integer getTempleId() {
        return templeId;
    }
    
    public Integer getUserId() {
        return userId;
    }

   
    
}

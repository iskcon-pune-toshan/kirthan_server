package org.iskon.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RolesSearch implements Serializable {

    
    private Integer id;

    private List<String> roleName;

 

    public Integer getId() {
        return id;
    }

    public List<String> getRoleName() {
        return roleName;
    }
    
}

package org.iskon.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PermissionsSearch implements Serializable {

    
    private Integer id;

    private List<String> name;

 

    public Integer getId() {
        return id;
    }

    public List<String> getName() {
        return name;
    }
    
}

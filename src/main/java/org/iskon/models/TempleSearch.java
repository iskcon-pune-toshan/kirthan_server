package org.iskon.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TempleSearch implements Serializable {

    
    private Integer id;

    private List<String> templeName;

    private List<String> city;

    public Integer getId() {
        return id;
    }

    public List<String> getTempleName() {
        return templeName;
    }

    public List<String> getCity() {
        return city;
    }

    
}

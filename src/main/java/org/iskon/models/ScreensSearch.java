package org.iskon.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ScreensSearch implements Serializable {

    
    private Integer id;

    private List<String> screenName;

 

    public Integer getId() {
        return id;
    }

    public List<String> getScreenName() {
        return screenName;
    }
    
}

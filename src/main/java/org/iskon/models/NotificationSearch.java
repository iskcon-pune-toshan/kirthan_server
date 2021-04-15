package org.iskon.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class NotificationSearch implements Serializable {

  
    private Date createdTime;
    private String dateInterval;

    public Date getCreatedTime() {
        return createdTime;
    }

	public String getDateInterval() {
		return dateInterval;
	}
    
}

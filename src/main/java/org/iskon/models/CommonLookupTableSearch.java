package org.iskon.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CommonLookupTableSearch implements Serializable {

    
    private Integer id;

    private String description;
    
    private String lookupType;

 

    public Integer getId() {
        return id;
    }


	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getLookupType() {
		return lookupType;
	}



	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}

   
    
}

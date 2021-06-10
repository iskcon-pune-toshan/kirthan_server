package org.iskon.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="lookups")
public class CommonLookupTable implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="lookup_type")
	private String lookupType;
	
	
	private CommonLookupTable(){

	}

	private CommonLookupTable(Integer id, String description, String lookupType) {
		this.id = id;
		this.description = description;
		this.lookupType = lookupType;
	}

	public static CommonLookupTable buildCommonLookupTable(Integer id, String description, String lookupType) {
		return new CommonLookupTable(id, description, lookupType);
	}

	public Integer getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}

	public String getLookupType() {
		return lookupType;
	}
	

}


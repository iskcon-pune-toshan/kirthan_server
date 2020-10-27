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
@Table(name="temple")
public class Temple implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="temple_name")
	private String templeName;
	
	@Column(name="area")
	private String area;
	
	@Column(name="city")
	private String city;
	
	private Temple(){

	}

	private Temple(Integer id, String templeName, String area, String city) {
		this.id = id;
		this.templeName = templeName;
		this.area = area;
		this.city = city;
	}

	public static Temple buildRoleScreen(Integer id, String templeName, String area, String city) {
		return new Temple(id, templeName, area, city);
	}

	public Integer getId() {
		return id;
	}
	
	public String getTempleName() {
		return templeName;
	}
	
	public String getArea() {
		return area;
	}
	
	public String getCity() {
		return city;
	}
	

}


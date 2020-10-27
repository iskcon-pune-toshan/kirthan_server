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
@Table(name="screens")
public class Screens implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="screen_name")
	private String screenName;
	
	
	private Screens(){

	}

	private Screens(Integer id, String screenName) {
		this.id = id;
		this.screenName = screenName;
	}

	public static Screens buildRoleScreen(Integer id, String screenName) {
		return new Screens(id, screenName);
	}

	public Integer getId() {
		return id;
	}
	
	public String getScreenName() {
		return screenName;
	}
	

}


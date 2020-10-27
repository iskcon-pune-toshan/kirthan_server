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
@Table(name="permissions")
public class Permissions implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	
	private Permissions(){

	}

	private Permissions(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public static Permissions buildRoleScreen(Integer id, String name) {
		return new Permissions(id, name);
	}

	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	

}


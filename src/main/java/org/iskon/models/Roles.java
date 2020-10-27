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
@Table(name="roles")
public class Roles implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="role_name")
	private String roleName;
	
	
	private Roles(){

	}

	private Roles(Integer id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}

	public static Roles buildRoleScreen(Integer id, String roleName) {
		return new Roles(id, roleName);
	}

	public Integer getId() {
		return id;
	}
	
	public String getRoleName() {
		return roleName;
	}
	

}


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
@Table(name="user_temple")
public class UserTemple implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="temple_id")
	private Integer templeId;
	
	@Column(name="role_id")
	private Integer roleId;
	
	@Column(name="user_id")
	private Integer userId;
	
	private UserTemple(){

	}

	private UserTemple(Integer id, Integer templeId, Integer roleId, Integer userId) {
		this.id = id;
		this.templeId = templeId;
		this.roleId = roleId;
		this.userId = userId;
	}

	public static UserTemple buildRoleScreen(Integer id, Integer templeId, Integer roleId, Integer userId) {
		return new UserTemple(id, templeId, roleId, userId);
	}

	public Integer getId() {
		return id;
	}
	
	public Integer getTempleId() {
		return templeId;
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	

}


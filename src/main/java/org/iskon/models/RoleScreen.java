package org.iskon.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="role_screen")
public class RoleScreen implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="role_id")
	private Integer roleId;
	
	@Column(name="screen_id")
	private Integer screenId;
	
	@Column(name="create")
	private Boolean create;

	@Column(name="update")
	private Boolean update;
	
	@Column(name="delete")
	private Boolean delete;

	@Column(name="view")
	private Boolean view;

	@Column(name="process")
	private Boolean process;
	
	@Transient
	private String roleName;
	
	@Transient
	private String screenName;
	
	
	private RoleScreen() {
		
	}

	private RoleScreen(Integer id, Integer roleId, Integer screenId, Boolean create, Boolean update, Boolean delete,
			Boolean view, Boolean process) {
		this.id = id;
		this.roleId = roleId;
		this.screenId = screenId;
		this.create = create;
		this.update = update;
		this.delete = delete;
		this.view = view;
		this.process = process;
	}

	public RoleScreen(Integer id, Integer roleId, Integer screenId, Boolean create, Boolean update
			, Boolean delete, Boolean view, Boolean process,String roleName,String screenName) {
		this.id = id;
		this.roleId = roleId;
		this.screenId = screenId;
		this.create = create;
		this.update = update;
		this.delete = delete;
		this.view = view;
		this.process = process;
		this.roleName = roleName;
		this.screenName = screenName;
	}

	public static RoleScreen buildRoleScreen(Integer id, Integer roleId, Integer screenId, Boolean create, Boolean update
			, Boolean delete, Boolean view, Boolean process) {
		return new RoleScreen(id, roleId, screenId, create, update, delete, view, process);
	}

	public Integer getId() {
		return id;
	}

	public Integer getRoleId() {
		return roleId;
	}
	
	public Integer getScreenId() {
		return screenId;
	}
	
	public Boolean getIsCreated() {
		return create;
	}
	
	public Boolean getIsUpdated() {
		return update;
	}
	
	public Boolean getIsDeleted() {
		return delete;
	}
	
	public Boolean getIsViewd() {
		return view;
	}
	
	public Boolean getIsProcessed() {
		return process;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public String getScreenName() {
		return screenName;
	}

}


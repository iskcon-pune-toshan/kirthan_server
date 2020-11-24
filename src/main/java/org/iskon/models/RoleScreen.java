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
	@Override
	public String toString() {
		return "RoleScreen [id=" + id + ", roleId=" + roleId + ", screenId=" + screenId + ", create=" + createFlag
				+ ", update=" + updateFlag + ", delete=" + deleteFlag + ", view=" + viewFlag + ", process=" + processFlag
				+ ", roleName=" + roleName + ", screenName=" + screenName + "]";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="role_id")
	private Integer roleId;
	
	@Column(name="screen_id")
	private Integer screenId;
	
	@Column(name="create_flag")
	private Boolean createFlag;

	@Column(name="update_flag")
	private Boolean updateFlag;
	
	@Column(name="delete_flag")
	private Boolean deleteFlag;

	@Column(name="view_flag")
	private Boolean viewFlag;

	@Column(name="process_flag")
	private Boolean processFlag;
	
	@Transient
	private String roleName;
	
	@Transient
	private String screenName;
	
	
	private RoleScreen() {
		
	}

	private RoleScreen(Integer id, Integer roleId, Integer screenId, Boolean createFlag, Boolean updateFlag, Boolean deleteFlag,
			Boolean viewFlag, Boolean processFlag) {
		this.id = id;
		this.roleId = roleId;
		this.screenId = screenId;
		this.createFlag = createFlag;
		this.updateFlag = updateFlag;
		this.deleteFlag = deleteFlag;
		this.viewFlag = viewFlag;
		this.processFlag = processFlag;
	}

	public RoleScreen(Integer id, Integer roleId, Integer screenId, Boolean createFlag, Boolean updateFlag
			, Boolean deleteFlag, Boolean viewFlag, Boolean processFlag,String roleName,String screenName) {
		this.id = id;
		this.roleId = roleId;
		this.screenId = screenId;
		this.createFlag = createFlag;
		this.updateFlag = updateFlag;
		this.deleteFlag = deleteFlag;
		this.viewFlag = viewFlag;
		this.processFlag = processFlag;
		this.roleName = roleName;
		this.screenName = screenName;
	}

	public static RoleScreen buildRoleScreen(Integer id, Integer roleId, Integer screenId, Boolean createFlag, Boolean updateFlag
			, Boolean deleteFlag, Boolean viewFlag, Boolean processFlag) {
		return new RoleScreen(id, roleId, screenId, createFlag, updateFlag, deleteFlag, viewFlag, processFlag);
	}

	public Integer getId() {
		return id;
	}

	/*
	 * public void setId(Integer id) { this.id = id; }
	 */

	public Integer getRoleId() {
		return roleId;
	}

	/*
	 * public void setRoleId(Integer roleId) { this.roleId = roleId; }
	 */

	public Integer getScreenId() {
		return screenId;
	}

	/*
	 * public void setScreenId(Integer screenId) { this.screenId = screenId; }
	 */


	public Boolean getCreateFlag() {
		return createFlag;
	}

	/*
	 * public void setCreateFlag(Boolean createFlag) { this.createFlag = createFlag;
	 * }
	 */
	public Boolean getUpdateFlag() {
		return updateFlag;
	}

	/*
	 * public void setUpdateFlag(Boolean updateFlag) { this.updateFlag = updateFlag;
	 * }
	 */

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	/*
	 * public void setDeleteFlag(Boolean deleteFlag) { this.deleteFlag = deleteFlag;
	 * }
	 */

	public Boolean getViewFlag() {
		return viewFlag;
	}

	/*
	 * public void setViewFlag(Boolean viewFlag) { this.viewFlag = viewFlag; }
	 */

	public Boolean getProcessFlag() {
		return processFlag;
	}

	/*
	 * public void setProcessFlag(Boolean processFlag) { this.processFlag =
	 * processFlag; }
	 */
	public String getRoleName() {
		return roleName;
	}

	/*
	 * public void setRoleName(String roleName) { this.roleName = roleName; }
	 */

	public String getScreenName() {
		return screenName;
	}

	/*
	 * public void setScreenName(String screenName) { this.screenName = screenName;
	 * }
	 */
	
	

	
}


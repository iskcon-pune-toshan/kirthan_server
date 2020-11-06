package org.iskon.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="notification_approval")
public class NotificationApproval implements Serializable {

	@Override
	public String toString() {
		return "NotificationApproval [id=" + id + ", uuid=" + uuid + ", message=" + message + ", targetType="
				+ targetType + ", targetId=" + targetId + ", action=" + action + ", mappingTableData="
				+ mappingTableData + ", broadcastType=" + broadcastType + ", title=" + title + ", createdBy="
				+ createdBy + ", updatedBy=" + updatedBy + ", createdTime=" + createdTime + ", updatedTime="
				+ updatedTime + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "message")
	private String message;

	@Column(name = "target_type")
	private String targetType;

	@Column(name = "target_id")
	private int targetId; // eventId or userid or team id

	@Column(name = "action")
	private String action; // Approve or Reject

	@Column(name = "mapping_table_data")
	private String mappingTableData;

	@Transient
	private String broadcastType;

	@Transient
	private String title;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "created_time")
	private Date createdTime;

	@Column(name = "updated_time")
	private Date updatedTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid.toString();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMappingTableData() {
		return mappingTableData;
	}

	public void setMappingTableData(String mappingTableData) {
		this.mappingTableData = mappingTableData;
	}

	public String getBroadcastType() {
		return broadcastType;
	}

	public void setBroadcastType(String broadcastType) {
		this.broadcastType = broadcastType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
}





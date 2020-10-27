package org.iskon.models;
	
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name="notification")
public class Notification implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "message")
	private String message;

	@Column(name = "target_type")
	private String targetType;

	@Column(name = "target_id")
	private int targetId;

	@Override
	public String toString() {
		return "Notification [id=" + id + ", uuid=" + uuid + ", message=" + message + ", targetType=" + targetType
				+ ", targetId=" + targetId + ", mappingTableData=" + mappingTableData + ", broadcastType="
				+ broadcastType + ", title=" + title + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + "]";
	}

	@Column(name = "mapping_table_data")
	private String mappingTableData;

	@Transient
	private String broadcastType;

	@Transient
	private String title;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "updated_by")
	private int updatedBy;

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

	public UUID getUuid() {
		return UUID.fromString(uuid);
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

	public int getCreatedBy() {
		return createdBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setUpdatedTime(java.util.Date updatedTime) {
		this.updatedTime = updatedTime;
	}
}

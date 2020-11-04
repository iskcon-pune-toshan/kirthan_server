package org.iskon.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="user_token")
public class UserToken implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_id")
	Integer userId;

	@Column(name = "device_token")
	private String deviceToken;

	@Column(name = "firebase_uid")
	private String firebaseUid;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "created_time")
	private Date createdTime;

	@Column(name = "updated_time")
	private Date updatedTime;

	private UserToken() {

	}

	private UserToken(Integer id, Integer userId, String deviceToken, String firebaseUid, String createdBy,
					  String updatedBy, Date createdTime, Date updatedTime) {
		this.id = id;
		this.userId = userId;
		this.deviceToken = deviceToken;
		this.firebaseUid = firebaseUid;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public static UserToken buildUserToken(Integer id, Integer userId, String deviceToken, String firebaseUid,
										   String createdBy, String updatedBy, Date Date1,
										   Date localDate2) {
		return new UserToken(id, userId, deviceToken, firebaseUid, createdBy, updatedBy, Date1, localDate2);
	}

	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public String getFirebaseUid() {
		return firebaseUid;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}
}

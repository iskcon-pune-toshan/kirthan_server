package org.iskon.models;

import java.time.LocalDateTime;

/*
create table user_token(
id int PRIMARY KEY AUTO_INCREMENT,
userId int not null,
deviceToken varchar(255) UNIQUE ,
fireBaseUid varchar(255) UNIQUE ,
createdBy int ,
createdAt timestamp,
updatedBy int ,
updateAt timestamp,
FOREIGN KEY(userId) references user_request(id)
);
 * */

public class UserTokenModel {
	@KeyField
	private int id;
	
	private int userId;
	
	@UpdateAllowed
	private String deviceToken;
	
	@UpdateAllowed
	private String firebaseUid;
	
	private int createdBy;
	private int updatedBy;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public UserTokenModel(int userId,
						  String deviceToken,
						  String firebaseUid,
						  int createdBy,
						  int updatedBy
						  ) {
		
	this.userId = userId;
	this.deviceToken = deviceToken;
	this.firebaseUid = firebaseUid;
	this.createdBy = createdBy;
	this.updatedBy = updatedBy;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	public String getfirebaseUid() {
		return this.firebaseUid;
	}
	public String getDeviceToken() {
		return this.deviceToken;
	}
	public int getUserId() {
		return this.userId;
	}
	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return this.updatedAt;
	}
}

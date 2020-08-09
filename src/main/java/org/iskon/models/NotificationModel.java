package org.iskon.models;
	
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

/*  id               | varchar(36)  | NO   | PRI | NULL                                 |                   |
| message          | varchar(255) | NO   |     | Kindly approve the following request |                   |
| type(server-genertated)             | varchar(10)  | NO   |     | APPR                                 |                   |
| createdAt        | timestamp    | NO   |     | CURRENT_TIMESTAMP                    | DEFAULT_GENERATED |
| createdBy        | int          | NO   |     | NULL                                 |                   |
| targetId         | int          | NO   |     | NULL                                 |                   |
| action           | varchar(20)  | NO   |     | NULL                                 |                   |
| mappingTableData | varchar(20)  | NO   |     | NULL                                 |                   |
| status
*/

public class NotificationModel {
	
	@KeyField
	private String title;
	
	private UUID id;
	private String type;
	private String message;
	private int createdBy;
	private LocalDateTime createdAt;
	private int targetId;
	private String mappingTableData;
	

	//creating a new notification
	public NotificationModel(Map<String,Object> data) {
		this.message =(String) data.get("message");
		this.type = (String)data.get("type");
		this.id = UUID.randomUUID();
		this.createdAt = LocalDateTime.now();
		this.createdBy =  (int) (data.get("userId"));
		this.targetId = (int) data.get("targetId");
		this.mappingTableData = (String) data.get("mappingTableData");
	}
	
	//reading an existing notification
	public NotificationModel(UUID id,Map<String,Object> ntf) {
		this.id = id;
		this.message =(String) ntf.get("message");
		this.type =(String) ntf.get("type");
		this.createdAt = (LocalDateTime) ntf.get("createdAt");
		this.createdBy = (int) ntf.get("createdBy");
		this.targetId = (int) ntf.get("targetId");
		this.mappingTableData = (String) ntf.get("mappingTableData");
	}
	
	public String getId() {
		return this.id.toString();
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMappingTable() {
		return this.mappingTableData;
	}
	
	public String getType() {
		return this.type;	
	}
	
	public String getMessage() {
		return this.message;
	}
	public int getTargetId() {
		return this.targetId;
	}
	public int getCreatorId() {
		return this.createdBy;
	}
	public LocalDateTime getTimeOfCreation() {
		return this.createdAt;
	}


	public void setMessage(String data) {
		this.message = data;
	}
	public void setId(String senderId) {
		System.out.println(senderId);
	}
	
}

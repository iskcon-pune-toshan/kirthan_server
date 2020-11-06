package org.iskon.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="event_user")
public class EventUser {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;          
	
	@Column(name = "event_id")
	private Integer eventId;   
	
	@Column(name = "team_id")
	private Integer teamId;      
	
	@Column(name="user_id")
	private Integer userId;      
	
	@Column(name = "created_by")
	private String createdBy;   
	
	@Column(name = "updated_by")
	private String updated_by;  
	
	@Column(name = "created_time")
	private int created_time;
	
	@Column(name = "updated_time")
	private int updatedTime;
}

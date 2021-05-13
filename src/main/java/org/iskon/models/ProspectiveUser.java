package org.iskon.models;

import javax.persistence.*;

import com.google.api.client.util.DateTime;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="prospective_user")
public class ProspectiveUser implements Serializable {
	

	@Override
	public String toString() {
		return "ProspectiveUser [id=" + id + ", userEmail=" + userEmail + ", invitedBy=" + invitedBy
				+ ", inviteCode=" + inviteCode + ", inviteType=" + inviteType + ", isProcessed="
						+ isProcessed + "]";
	}


	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "invited_by")
	private String invitedBy;
	
	@Column(name = "invite_code")
	private String inviteCode;

	@Column(name = "invite_type")
	private String inviteType;
	
	@Column(name = "is_processed")
	private Boolean isProcessed;


	private ProspectiveUser(){

	}

	private ProspectiveUser(Integer id, String userEmail, String invitedBy, String inviteType,
				  String inviteCode, Boolean isProcessed ) {
		this.id = id;
		this.userEmail = userEmail;
		this.invitedBy = invitedBy;
		this.inviteCode = inviteCode;
		this.inviteType = inviteType;
		this.isProcessed = isProcessed;
	}

	public static ProspectiveUser buildProspectiveUser(Integer id, String userEmail, String invitedBy, String inviteType,
			  String inviteCode, Boolean isProcessed) {
		return new ProspectiveUser(id, userEmail, invitedBy, inviteType, inviteCode, isProcessed);
	}

	public Integer getId() {
		return id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getInvitedBy() {
		return invitedBy;
	}

	public String getInviteType() {
		return inviteType;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public Boolean getIsProcessed() {
		return isProcessed;
	}
	
	public void setIsProcessed(Boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
}
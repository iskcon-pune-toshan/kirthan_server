package org.iskon.models;

public class ProspectiveUserSearch {
	private Integer id;
	private String userEmail;
	private String localAdminEmail;
	private String inviteType;
	private String inviteCode;
	 private Boolean isProcessed;
	
	public Integer getId() {
		return id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getLocalAdminEmail() {
		return localAdminEmail;
	}

	public void setLocalAdminEmail(String localAdminEmail) {
		this.localAdminEmail = localAdminEmail;
	}

	public String getInviteType() {
		return inviteType;
	}

	public void setInviteType(String inviteType) {
		this.inviteType = inviteType;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public Boolean getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
}

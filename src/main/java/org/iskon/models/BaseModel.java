package org.iskon.models;

import java.util.Date;

public class BaseModel {

	String createdBy;
	@UpdateAllowed
	String updatedBy;
	Date createTime;
	@UpdateAllowed
	Date updateTime;


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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/*
	 * public String getJson() { ObjectMapper mapper = new ObjectMapper();
	 * mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); try {
	 * return mapper.writeValueAsString(this); } catch (JsonProcessingException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } return null; }
	 */

}


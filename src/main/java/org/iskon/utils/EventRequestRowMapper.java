package org.iskon.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.iskon.models.EventRequest;
import org.springframework.jdbc.core.RowMapper;

public class EventRequestRowMapper implements RowMapper<EventRequest> {

	@Override
	public EventRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		EventRequest er = new EventRequest();
		er.setId(rs.getInt("Id"));
		er.setEventTitle(rs.getString("eventTitle"));
		er.setEventDescription(rs.getString("eventDescription"));
		er.setEventDate(rs.getDate("eventDate"));
		er.setEventDuration(rs.getString("eventDuration"));
		er.setEventLocation(rs.getString("eventLocation"));
		er.setEventType(rs.getString("eventType"));
		er.setPhoneNumber(rs.getLong("phoneNumber"));
		er.setAddLineOne(rs.getString("addlineone"));
		er.setAddLineTwo(rs.getString("addlinetwo"));
		er.setAddLineThree(rs.getString("addlinethree"));
		er.setLocality(rs.getString("locality"));
		er.setCity(rs.getString("city"));
		er.setPinCode(rs.getInt("pincode"));
		er.setState(rs.getString("state"));
		er.setIsProcessed(rs.getBoolean("isprocessed"));
		er.setCreatedBy(rs.getString("createdby"));
		er.setUpdatedBy(rs.getString("updatedby"));
		er.setCreateTime(rs.getDate("createtime"));
		er.setUpdateTime(rs.getDate("updatetime"));
		er.setApprovalStatus(rs.getString("approvalstatus"));
		er.setApprovalComments(rs.getString("approvalcomments"));
		// TODO Auto-generated method stub
		return er;
	}


}

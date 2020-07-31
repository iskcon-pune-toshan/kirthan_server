package org.iskon.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.iskon.models.EventTeamUserMapping;
import org.springframework.jdbc.core.RowMapper;


public class EventTeamUserMappingRowMapper implements RowMapper<EventTeamUserMapping> {
	
	@Override
	public EventTeamUserMapping mapRow(ResultSet rs, int rowNum) throws SQLException {

		EventTeamUserMapping eventteamUser = new EventTeamUserMapping();
		eventteamUser.setId(rs.getInt("id"));
		eventteamUser.setEventId(rs.getInt("eventid"));
		eventteamUser.setTeamId(rs.getInt("teamid"));
		eventteamUser.setUserId(rs.getInt("userid"));
		eventteamUser.setEventName(rs.getString("eventname"));
		eventteamUser.setTeamName(rs.getString("teamname"));
		eventteamUser.setUserName(rs.getString("username"));
		eventteamUser.setCreatedBy(rs.getString("createdby"));
		eventteamUser.setUpdatedBy(rs.getString("updatedby"));
		eventteamUser.setCreateTime(rs.getDate("createtime"));
		eventteamUser.setUpdateTime(rs.getDate("updatetime"));
		
		return eventteamUser;
	}

}

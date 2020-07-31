package org.iskon.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.iskon.models.TeamUserMapping;
import org.springframework.jdbc.core.RowMapper;


public class TeamUserMappingRowMapper implements RowMapper<TeamUserMapping> {

	@Override
	public TeamUserMapping mapRow(ResultSet rs, int rowNum) throws SQLException {

		TeamUserMapping teamUser = new TeamUserMapping();
		teamUser.setId(rs.getInt("id"));
		teamUser.setTeamId(rs.getInt("teamid"));
		teamUser.setUserId(rs.getInt("userid"));
		teamUser.setUserName(rs.getString("username"));
		teamUser.setTeamName(rs.getString("teamname"));
		teamUser.setCreatedBy(rs.getString("createdby"));
		teamUser.setUpdatedBy(rs.getString("updatedby"));
		teamUser.setCreateTime(rs.getDate("createtime"));
		teamUser.setUpdateTime(rs.getDate("updatetime"));
		
		return teamUser;
	}

}

package org.iskon.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.iskon.models.TeamUserMapping;
import org.springframework.jdbc.core.RowMapper;


public class TeamUserMappingRowMapper implements RowMapper<TeamUserMapping> {

	@Override
	public TeamUserMapping mapRow(ResultSet rs, int rowNum) throws SQLException {

		TeamUserMapping ur = new TeamUserMapping();
		ur.setId(rs.getInt("id"));
		ur.setTeamid(rs.getInt("teamid"));
		ur.setUserid(rs.getInt("userid"));
		return ur;
	}

}

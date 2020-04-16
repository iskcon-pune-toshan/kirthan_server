package org.iskon.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.iskon.models.TeamRequest;
import org.springframework.jdbc.core.RowMapper;


public class TeamRequestRowMapper implements RowMapper<TeamRequest> {

	@Override
	public TeamRequest mapRow(ResultSet rs, int rowNum) throws SQLException {

		TeamRequest tr = new TeamRequest();
		tr.setTeamId(rs.getInt("teamId"));
		tr.setTeamTitle(rs.getString("teamTitle"));
		tr.setTeamDescription(rs.getString("teamDescription"));
		tr.setIsProcessed(rs.getBoolean("isprocessed"));
		tr.setCreatedBy(rs.getString("createdby"));
		tr.setUpdatedBy(rs.getString("updatedby"));
		tr.setCreateTime(rs.getDate("createtime"));
		tr.setUpdateTime(rs.getDate("updatetime"));
		tr.setApprovalStatus(rs.getString("approvalstatus"));
		tr.setApprovalComments(rs.getString("approvalcomments"));

		return tr;
		
		
	}

}
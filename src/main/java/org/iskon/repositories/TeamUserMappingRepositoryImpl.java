package org.iskon.repositories;

import java.util.List;
import java.util.Map;

import org.iskon.models.TeamUserMapping;
import org.iskon.utils.FieldCacheType;
import org.iskon.utils.JdbcModelHelper;
import org.iskon.utils.QueryBuilder;
import org.iskon.utils.TeamUserMappingRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

@Component
public class TeamUserMappingRepositoryImpl implements TeamUserMappingRepository {

	private static String TableName = "team_user_mapping";
	@Autowired
	JdbcTemplate jdbcTemplate;

	// @Autowired
	JdbcModelHelper jdbcModelHelper;

	@Autowired
	QueryBuilder queryBuilder;

	@Autowired
	public TeamUserMappingRepositoryImpl(JdbcModelHelper jdbcModelHelper) {
		this.jdbcModelHelper = jdbcModelHelper;
		this.jdbcModelHelper.prepareObject(TeamUserMapping.class);
	}

	@Override
	public TeamUserMapping submitNewTeamUserMapping(TeamUserMapping newTeamUserMapping) {
		//newTeamUserMapping.setApprovalStatus("NEW");
		// jdbcSimpleInsertHelper.prepareObject(newUserRequest);
		List<String> columns = jdbcModelHelper.getColumns(newTeamUserMapping, FieldCacheType.ForInsert);
		Map<String, Object> objectMap = jdbcModelHelper.getDataMap(newTeamUserMapping, FieldCacheType.ForInsert);
		
		System.out.println("Before: "+columns.size());
		columns.remove("teamname");
		columns.remove("username");
		System.out.println("After: "+columns.size());

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.setTableName(TableName);
		simpleJdbcInsert.setColumnNames(columns);
		simpleJdbcInsert.setGeneratedKeyName("id");

		Number id = simpleJdbcInsert.executeAndReturnKey(objectMap);
		newTeamUserMapping.setId(id.intValue());

		return newTeamUserMapping;
	}


	@Override
	public TeamUserMapping submitDeleteTeamUserMapping(TeamUserMapping newTeamUserMapping) {

		int rows = jdbcTemplate.update("delete from team_user_mapping where id = ?", newTeamUserMapping.getId());

		System.out.println("rows deleted: " + rows);

		return newTeamUserMapping;
	}

	@Override
	public List<TeamUserMapping> getTeamUserMappings(Map<String, Object> queryMap) {
		
		String strQuery = "select TUM.*, TR.teamTitle as teamname, concat(UR.firstname, ' ',UR.lastname) as username " +
				" from kirtan.team_user_mapping TUM, kirtan.team_request TR, kirtan.user_request UR " +
				" where TUM.userId = UR.id " +
				" and TUM.teamId = TR.id and TUM.";
		
		//String strQuery = "select * from team_user_mapping";

		String query = queryBuilder.getSimpleAndQueryFromMap(strQuery, queryMap,false);

		MapSqlParameterSource queryParams = queryBuilder.getNamedQueryParametersFromMap(queryMap);

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

		List<TeamUserMapping> teamRequests = template.query(query, queryParams, new TeamUserMappingRowMapper());

		return teamRequests;
	}

}

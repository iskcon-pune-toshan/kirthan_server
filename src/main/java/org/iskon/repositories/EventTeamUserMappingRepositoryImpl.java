package org.iskon.repositories;

import java.util.List;
import java.util.Map;

import org.iskon.models.EventTeamUserMapping;
import org.iskon.utils.EventTeamUserMappingRowMapper;
import org.iskon.utils.FieldCacheType;
import org.iskon.utils.JdbcModelHelper;
import org.iskon.utils.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

@Component
public class EventTeamUserMappingRepositoryImpl implements EventTeamUserMappingRepository {

	private static String TableName = "event_user_mapping";
	@Autowired
	JdbcTemplate jdbcTemplate;

	// @Autowired
	JdbcModelHelper jdbcModelHelper;

	@Autowired
	QueryBuilder queryBuilder;

	@Autowired
	public EventTeamUserMappingRepositoryImpl(JdbcModelHelper jdbcModelHelper) {
		this.jdbcModelHelper = jdbcModelHelper;
		this.jdbcModelHelper.prepareObject(EventTeamUserMapping.class);
	}

	@Override
	public EventTeamUserMapping submitNewEventTeamUserMapping(EventTeamUserMapping newEventTeamUserMapping) {
		//newTeamUserMapping.setApprovalStatus("NEW");
		// jdbcSimpleInsertHelper.prepareObject(newUserRequest);
		List<String> columns = jdbcModelHelper.getColumns(newEventTeamUserMapping, FieldCacheType.ForInsert);
		Map<String, Object> objectMap = jdbcModelHelper.getDataMap(newEventTeamUserMapping, FieldCacheType.ForInsert);
		
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.setTableName(TableName);
		simpleJdbcInsert.setColumnNames(columns);
		simpleJdbcInsert.setGeneratedKeyName("id");

		Number id = simpleJdbcInsert.executeAndReturnKey(objectMap);
		newEventTeamUserMapping.setId(id.intValue());

		return newEventTeamUserMapping;
	}


	@Override
	public EventTeamUserMapping submitDeleteEventTeamUserMapping(EventTeamUserMapping newEventTeamUserMapping) {

		int rows = jdbcTemplate.update("delete from event_user_mapping where id = ?", newEventTeamUserMapping.getId());

		System.out.println("rows deleted: " + rows);

		return newEventTeamUserMapping;
	}
	
	
	@Override
	public List<EventTeamUserMapping> getEventTeamUserMappings(Map<String, Object> queryMap) {
		
		String strQuery = "select EUM.*, TR.teamTitle as teamname, concat(UR.firstname, ' ',UR.lastname) as username, ER.eventTitle as eventname " + 
				"from kirtan.event_user_mapping EUM, kirtan.team_request TR, kirtan.user_request UR, kirtan.event_request ER " + 
				"where EUM.userId = UR.id " + 
				"and EUM.teamId = TR.id " + 
				"and  EUM.eventId= ER.id and EUM.";
		

		String query = queryBuilder.getSimpleAndQueryFromMap(strQuery, queryMap, false);

		MapSqlParameterSource queryParams = queryBuilder.getNamedQueryParametersFromMap(queryMap);

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

		List<EventTeamUserMapping> teamRequests = template.query(query, queryParams, new EventTeamUserMappingRowMapper());

		return teamRequests;
	}	
}

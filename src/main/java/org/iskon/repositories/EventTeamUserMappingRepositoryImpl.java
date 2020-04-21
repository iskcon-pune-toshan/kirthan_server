package org.iskon.repositories;

import java.util.List;
import java.util.Map;

import org.iskon.models.EventTeamUserMapping;
import org.iskon.utils.FieldCacheType;
import org.iskon.utils.JdbcModelHelper;
import org.iskon.utils.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.iskon.models.UserRequest;
import org.iskon.utils.FieldCacheType;
import org.iskon.utils.JdbcModelHelper;

import org.iskon.utils.QueryBuilder;
import org.iskon.utils.EventTeamUserMappingRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class EventTeamUserMappingRepositoryImpl implements EventTeamUserMappingRepository {

	private static String TableName = "event_team_user_mapping";
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

		int rows = jdbcTemplate.update("delete from event_team_user_mapping where id = ?", newEventTeamUserMapping.getId());

		System.out.println("rows deleted: " + rows);

		return newEventTeamUserMapping;
	}
}

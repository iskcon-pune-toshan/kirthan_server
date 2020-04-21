package org.iskon.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iskon.models.TeamUserMapping;

import org.iskon.utils.FieldCacheType;
import org.iskon.utils.JdbcModelHelper;

import org.iskon.utils.QueryBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

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


}

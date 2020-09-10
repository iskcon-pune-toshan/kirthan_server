package org.iskon.repositories;


import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iskon.models.TeamRequest;
import org.iskon.utils.FieldCacheType;
import org.iskon.utils.JdbcModelHelper;
//import org.iskon.utils.JdbcSimpleInsertHelper;
import org.iskon.utils.QueryBuilder;
import org.iskon.utils.TeamRequestRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;


@Component
public class TeamRequestRepositoryImpl implements TeamRequestRepository{
	
	private static String TableName = "team_request";
	@Autowired
	JdbcTemplate jdbcTemplate;

	// @Autowired
	JdbcModelHelper jdbcModelHelper;

	@Autowired
	QueryBuilder queryBuilder;

	@Autowired
	public TeamRequestRepositoryImpl(JdbcModelHelper jdbcModelHelper) {
		this.jdbcModelHelper = jdbcModelHelper;
		this.jdbcModelHelper.prepareObject(TeamRequest.class);
	}
	
	@Override
	public TeamRequest submitNewTeamRequest(TeamRequest newTeamRequest) {
		newTeamRequest.setApprovalStatus("NEW");
		// jdbcSimpleInsertHelper.prepareObject(newUserRequest);
		List<String> columns = jdbcModelHelper.getColumns(newTeamRequest, FieldCacheType.ForInsert);
		Map<String, Object> objectMap = jdbcModelHelper.getDataMap(newTeamRequest, FieldCacheType.ForInsert);

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.setTableName(TableName);
		simpleJdbcInsert.setColumnNames(columns);
		simpleJdbcInsert.setGeneratedKeyName("Id");

		Number teamid = simpleJdbcInsert.executeAndReturnKey(objectMap);
		newTeamRequest.setId(teamid.intValue());

		return newTeamRequest;
	}
	
	@Override
	public List<TeamRequest> getTeamRequests(Map<String, Object> queryMap) {

		String query = queryBuilder.getSimpleAndQueryFromMap("select * from team_request", queryMap,true);

		MapSqlParameterSource queryParams = queryBuilder.getNamedQueryParametersFromMap(queryMap);

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

		List<TeamRequest> teamRequests = template.query(query, queryParams, new TeamRequestRowMapper());

		return teamRequests;
	}


	@Override
	public Boolean processTeamRequest(Integer id, String approvalstatus, String approvalcomments,
			String updatedby) {
		    
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
		            .withProcedureName("process_team_request")
		            . declareParameters(
		                    new SqlParameter("teamId", Types.INTEGER),
		                    new SqlParameter("approvalstatus", Types.VARCHAR),
		                    new SqlParameter("approvalcomments", Types.VARCHAR),
		                    new SqlParameter("updatedby", Types.VARCHAR),
		                    new SqlParameter("updatetime", Types.TIMESTAMP),
		                    new SqlOutParameter("operationstatus", Types.BOOLEAN),
		                    new SqlOutParameter("errormessage", Types.VARCHAR));

			
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("teamId", id);
			inParamMap.put("approvalstatus", approvalstatus);
			inParamMap.put("approvalcomments", approvalcomments);
			inParamMap.put("updatedby", updatedby);
			inParamMap.put("updatetime", new Date());
		    Map<String, Object> execute = call.execute(
		    		new MapSqlParameterSource(inParamMap));
		    
		    return (Boolean) execute.get("operationstatus");
		}
	
	@Override
	public TeamRequest submitUpdateTeamRequest(TeamRequest newTeamRequest) {
		//newTeamRequest.setApprovalStatus("UPDATE");
		
		Map.Entry<String, Object[]> kvpOfQueryAndArgs = queryBuilder.getUpdateEntryData(TableName, newTeamRequest);

		int rows = jdbcTemplate.update(kvpOfQueryAndArgs.getKey(), kvpOfQueryAndArgs.getValue());

		System.out.println("rows updated: " + rows);

		return newTeamRequest;
	}

	@Override
	public TeamRequest submitDeleteTeamRequest(TeamRequest newTeamRequest) {

		int rows = jdbcTemplate.update("delete from team_request where id = ?", newTeamRequest.getId());

		System.out.println("rows deleted: " + rows);

		return newTeamRequest;
	}

	@Override
	public List<Integer> getEventRequestsCountByStatus(){
			ArrayList<Integer> data = new ArrayList<>();
			ArrayList<String> status = new ArrayList<>();
			status.add("Approved");
			status.add("NEW");
			status.add("Rejected");
			String sql;
			for(String i : status){
				sql = String.format("Select count(id) from team_request where approvalstatus = '%s'", i);
				data.add(this.jdbcTemplate.query(sql,(rs)->{
					rs.next();
					return (rs.getInt("count(id)"));
				}));	
			}
				return data;
	}
	
}

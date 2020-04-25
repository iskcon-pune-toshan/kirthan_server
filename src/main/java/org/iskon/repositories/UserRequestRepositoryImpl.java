package org.iskon.repositories;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iskon.models.UserRequest;
import org.iskon.utils.FieldCacheType;
import org.iskon.utils.JdbcModelHelper;
import org.iskon.utils.QueryBuilder;
import org.iskon.utils.UserRequestRowMapper;
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
public class UserRequestRepositoryImpl implements UserRequestRepository {

	private static String TableName = "user_request";
	@Autowired
	JdbcTemplate jdbcTemplate;

	// @Autowired
	JdbcModelHelper jdbcModelHelper;

	@Autowired
	QueryBuilder queryBuilder;

	@Autowired
	public UserRequestRepositoryImpl(JdbcModelHelper jdbcModelHelper) {
		this.jdbcModelHelper = jdbcModelHelper;
		this.jdbcModelHelper.prepareObject(UserRequest.class);
	}

	@Override
	public UserRequest submitNewUserRequest(UserRequest newUserRequest) {
		newUserRequest.setApprovalStatus("NEW");
		// jdbcSimpleInsertHelper.prepareObject(newUserRequest);
		List<String> columns = jdbcModelHelper.getColumns(newUserRequest, FieldCacheType.ForInsert);
		Map<String, Object> objectMap = jdbcModelHelper.getDataMap(newUserRequest, FieldCacheType.ForInsert);

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.setTableName(TableName);
		simpleJdbcInsert.setColumnNames(columns);
		simpleJdbcInsert.setGeneratedKeyName("id");

		Number id = simpleJdbcInsert.executeAndReturnKey(objectMap);
		newUserRequest.setId(id.intValue());

		return newUserRequest;
	}

	@Override
	public UserRequest submitUpdateUserRequest(UserRequest newUserRequest) {
		//newUserRequest.setApprovalStatus("UPDATE");
		
		Map.Entry<String, Object[]> kvpOfQueryAndArgs = queryBuilder.getUpdateEntryData(TableName, newUserRequest);

		int rows = jdbcTemplate.update(kvpOfQueryAndArgs.getKey(), kvpOfQueryAndArgs.getValue());

		System.out.println("rows updated: " + rows);

		return newUserRequest;
	}

	@Override
	public UserRequest submitDeleteUserRequest(UserRequest newUserRequest) {

		int rows = jdbcTemplate.update("delete from user_request where id = ?", newUserRequest.getId());

		System.out.println("rows deleted: " + rows);

		return newUserRequest;
	}

	@Override
	public List<UserRequest> getUserRequests(Map<String, Object> queryMap) {

		String query = queryBuilder.getSimpleAndQueryFromMap("select * from user_request", queryMap);

		MapSqlParameterSource queryParams = queryBuilder.getNamedQueryParametersFromMap(queryMap);

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

		List<UserRequest> userRequests = template.query(query, queryParams, new UserRequestRowMapper());

		return userRequests;
	}

	@Override
	public Boolean processUserRequest(Integer id, String approvalstatus, String approvalcomments, String usertype,
			String updatedby) {
		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withProcedureName("process_user_request")
				.declareParameters(new SqlParameter("id", Types.INTEGER),
						new SqlParameter("approvalstatus", Types.VARCHAR),
						new SqlParameter("approvalcomments", Types.VARCHAR),
						new SqlParameter("usertype", Types.VARCHAR), new SqlParameter("updatedby", Types.VARCHAR),
						new SqlParameter("updatetime", Types.TIMESTAMP),
						new SqlOutParameter("operationstatus", Types.BOOLEAN),
						new SqlOutParameter("errormessage", Types.VARCHAR));

		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("id", id);
		inParamMap.put("approvalstatus", approvalstatus);
		inParamMap.put("approvalcomments", approvalcomments);
		inParamMap.put("usertype", usertype);
		inParamMap.put("updatedby", updatedby);
		inParamMap.put("updatetime", new Date());
		Map<String, Object> execute = call.execute(new MapSqlParameterSource(inParamMap));

		return (Boolean) execute.get("operationstatus");
	}

}

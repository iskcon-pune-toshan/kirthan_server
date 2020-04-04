package org.iskon.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.iskon.models.UserRequest;
import org.iskon.utils.JdbcSimpleInsertHelper;
import org.iskon.utils.QueryBuilder;
import org.iskon.utils.UserRequestRowMapper;
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
public class UserRequestRepositoryImpl implements UserRequestRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	JdbcSimpleInsertHelper jdbcSimpleInsertHelper;

	@Autowired
	QueryBuilder queryBuilder;

	public UserRequestRepositoryImpl() {

	}

	@Override
	public UserRequest submitNewUserRequest(UserRequest newUserRequest) {
		newUserRequest.setApprovalStatus("NEW");
		jdbcSimpleInsertHelper.prepareObject(newUserRequest);
		List<String> columns = jdbcSimpleInsertHelper.getColumns(newUserRequest);
		Map<String, Object> objectMap = jdbcSimpleInsertHelper.getDataMap(newUserRequest);

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.setTableName("user_request");
		simpleJdbcInsert.setColumnNames(columns);
		simpleJdbcInsert.setGeneratedKeyName("id");

		Number id = simpleJdbcInsert.executeAndReturnKey(objectMap);
		newUserRequest.setId(id.intValue());

		return newUserRequest;
	}
	
	@Override
	public UserRequest submitUpdateUserRequest(UserRequest newUserRequest) {
		newUserRequest.setApprovalStatus("UPDATE");
		
/*		jdbcSimpleInsertHelper.prepareObject(newUserRequest);
		List<String> columns = jdbcSimpleInsertHelper.getColumns(newUserRequest);
		Map<String, Object> objectMap = jdbcSimpleInsertHelper.getDataMap(newUserRequest);
		
//		jdbcTemplate.exec
		
//		jdbcTemplate.update(psc, generatedKeyHolder)
		String sql = "update user_request set updatedby = ? where id = ?";
		ParsedSql parsedSql = getParsedSql(sql);
		String sqlToUse = NamedParameterUtils.substituteNamedParameters(parsedSql, paramSource);
		Object[] params = NamedParameterUtils.buildValueArray(parsedSql, paramSource, null);
		List<SqlParameter> declaredParameters = NamedParameterUtils.buildSqlParameterList(parsedSql, paramSource);
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sqlToUse, declaredParameters);
		
		PreparedStatementCreator psc = new PreparedStatementCreatorFactory("update user_request set updatedby = ? where id = ?", declaredParameters)
		
		jdbcTemplate.update(psc);
		
		*/
		
		
		int rows =jdbcTemplate.update("update user_request set updatedby = ? where id = ?", 
							newUserRequest.getUpdatedBy(), 
							newUserRequest.getId());
		
		System.out.println("rows updated: "+rows);

		return newUserRequest;
	}

	@Override
	public UserRequest submitDeleteUserRequest(UserRequest newUserRequest) {
		
		int rows = jdbcTemplate.update("delete from user_request where id = ?",newUserRequest.getId());
		
		System.out.println("rows deleted: "+rows);

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
	public Boolean processUserRequest(Integer id, String approvalstatus, String approvalcomments,
			String usertype, String updatedby) {
		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
	            .withProcedureName("process_user_request")
	            . declareParameters(
	                    new SqlParameter("id", Types.INTEGER),
	                    new SqlParameter("approvalstatus", Types.VARCHAR),
	                    new SqlParameter("approvalcomments", Types.VARCHAR),
	                    new SqlParameter("usertype", Types.VARCHAR),
	                    new SqlParameter("updatedby", Types.VARCHAR),
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
	    Map<String, Object> execute = call.execute(
	    		new MapSqlParameterSource(inParamMap));
	    
	    return (Boolean) execute.get("operationstatus");
	}
	
}

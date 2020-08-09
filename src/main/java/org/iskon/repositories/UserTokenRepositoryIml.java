package org.iskon.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iskon.models.UserTokenModel;
import org.iskon.utils.FieldCacheType;
import org.iskon.utils.JdbcModelHelper;
import org.iskon.utils.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class UserTokenRepositoryIml implements UserTokenRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private QueryBuilder queryBuilder;
	
	private JdbcModelHelper jdbcModelHelper;
	
	@Autowired
	public UserTokenRepositoryIml(JdbcModelHelper jdbcModelHelper) {
		this.jdbcModelHelper = jdbcModelHelper;
		this.jdbcModelHelper.prepareObject(UserTokenModel.class);
	}
	
	@Override
	public String fetchDeviceToken(int userId) {
		Map<String,Object> inputParam = new HashMap<>();
		inputParam.put("userId",userId);
		String sqlQuery = this.queryBuilder.getSimpleAndQueryFromMap(
				"Select deviceToken from user_token", 
				inputParam,true);
		MapSqlParameterSource queryMap =this.queryBuilder.getNamedQueryParametersFromMap(inputParam);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(this.jdbcTemplate);		
		String token = template.query(sqlQuery,queryMap,(rs)->{
			if(rs.next())
				return rs.getString("deviceToken");
			else
				return null;
		});		
		return token;
	}
	
	public List<String> fetchDeviceToken(List<Integer> userId) {
		if(userId == null)
			return null;
		MapSqlParameterSource params = new MapSqlParameterSource("userIds",userId);
		String sqlQuery = "SELECT DISTINCT deviceToken from user_token where userId in (:userIds)";
		
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(this.jdbcTemplate);		
		List<String> tokenList = template.query(sqlQuery,params,(rs,rowNumber)->{
				return rs.getString("deviceToken");
		});		
		
		return tokenList;
	}
	@Override
	public Boolean storeTokens(UserTokenModel user) {
	try {	
		List<String> columnNames = this.jdbcModelHelper.getColumns(user, FieldCacheType.ForInsert);
		Map<String,Object> queryParams = this.jdbcModelHelper.getDataMap(user, FieldCacheType.ForInsert);
		System.out.println(columnNames);
		System.out.println(queryParams);

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate);
		simpleJdbcInsert.setTableName("user_token");
		simpleJdbcInsert.setColumnNames(columnNames);
		simpleJdbcInsert.setGeneratedKeyName("Id");
		
		Number id = simpleJdbcInsert.executeAndReturnKey(queryParams);
		user.setId(id.intValue()); 
		return true;
	}
	catch(Exception err) {
		System.out.println(err.getMessage());
		return false;
	}
		
	}
	
	@Override
	public Boolean updateToken(int userId, String token) {
		String sql =" update user_token set deviceToken =:devicetoken where userId =:userid";
		Map<String,Object> queryMap = new HashMap<>();
		queryMap.put("deviceToken",token);
		queryMap.put("userId",userId);
		System.out.println(queryMap);
		MapSqlParameterSource param = this.queryBuilder.getNamedQueryParametersFromMap(queryMap);
		System.out.println(param);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(this.jdbcTemplate);
		int rows = template.update(sql,param);
		if(rows<=0)
			return false;
		else
			return true;
	}

}

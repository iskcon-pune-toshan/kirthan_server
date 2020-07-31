package org.iskon.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iskon.models.NotificationListModel;
import org.iskon.utils.FieldCacheType;
import org.iskon.utils.JdbcModelHelper;
import org.iskon.utils.NotificationListModelRowMapper;
import org.iskon.utils.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationListRepositoryIml implements NotificationListRepository{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private QueryBuilder queryBuilder;
	
	private JdbcModelHelper jdbcModelHelper;
	
	@Autowired
	public NotificationListRepositoryIml(JdbcModelHelper jdbcModelHelper) {
		this.jdbcModelHelper = jdbcModelHelper;
		this.jdbcModelHelper.prepareObject(NotificationListModel.class);
	}
	
	
	@Override
	public List<NotificationListModel> userList(int eventId){
		// TODO Auto-generated method stub
		try {
		Map<String,Object> inputParam = new HashMap<>();
		
		inputParam.put("groupId",eventId);
		
		String SQL_Query= queryBuilder.getSimpleAndQueryFromMap(
				" select * from notification_list ", inputParam);
		MapSqlParameterSource params = queryBuilder.getNamedQueryParametersFromMap(inputParam);
		
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
		
		List<NotificationListModel> result = namedJdbcTemplate.query(SQL_Query,params, new NotificationListModelRowMapper());
		if(result==null)
			throw new Exception("user not found");
		return result;		
		}
		catch(Exception err){			
			System.out.println(err.getMessage());
			return null;
		}		
	}

	@Override
	public Boolean addUser(NotificationListModel userDetails) {
	try {	
		List<String>	columns = this.jdbcModelHelper.getColumns(userDetails,FieldCacheType.ForInsert);
		Map<String,Object> dataMap = this.jdbcModelHelper.getDataMap(userDetails, FieldCacheType.ForInsert);
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate);
		simpleJdbcInsert.setTableName("notification_list");
		simpleJdbcInsert.setColumnNames(columns);
		simpleJdbcInsert.setGeneratedKeyName("Id");
		System.out.println(columns.toString()+dataMap.toString());	
		simpleJdbcInsert.executeAndReturnKey(dataMap);
		return true;
	}
	catch(Exception err) {
		System.out.println(err.getMessage());
		return false;
		}
	}

	@Override
	public Boolean removeUser(Map<String, Object> body) {
		try {	
			Map<String,Object> queryMap = new HashMap<>();
			queryMap.put("userId",body.get("userId"));
			queryMap.put("groupId",body.get("groupId"));
			queryMap.put("userType",body.get("userType"));
			String sqlQuery = this.queryBuilder.getSimpleAndQueryFromMap(
				"delete from notification_list", queryMap);
			MapSqlParameterSource params = this.queryBuilder.getNamedQueryParametersFromMap(queryMap);
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(this.jdbcTemplate);
			int result = template.update(sqlQuery,params);
			if(result<=0) {
				return false;
			}
			else {
				return true;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}

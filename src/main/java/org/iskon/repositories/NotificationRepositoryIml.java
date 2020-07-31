package org.iskon.repositories;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.iskon.models.NotificationListModel;
import org.iskon.models.NotificationModel;
import org.iskon.models.UserTokenModel;
import org.iskon.utils.FieldCacheType;
import org.iskon.utils.JdbcModelHelper;
import org.iskon.utils.NotificationListModelRowMapper;
import org.iskon.utils.NotificationModelRowMapper;
import org.iskon.utils.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepositoryIml implements NotificationRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private JdbcModelHelper jdbcModelHelper;
	@Autowired
	private QueryBuilder queryBuilder;

	@Autowired
	public NotificationRepositoryIml(JdbcModelHelper jdbcModelHelper) {
		this.jdbcModelHelper = jdbcModelHelper;
		this.jdbcModelHelper.prepareObject(NotificationModel.class);
		this.jdbcModelHelper.prepareObject(NotificationListModel.class);
		this.jdbcModelHelper.prepareObject(UserTokenModel.class);
	}

	@Override
	public List<NotificationModel> getAll(int userId) {
		Map<String, Object> inputQueryParam = new HashMap<>();
		inputQueryParam.put("list.userId", userId);
		inputQueryParam.put("targetId", userId);
		String SQL_QUERY = queryBuilder
				.getSimpleOrQueryFromMap(
						"SELECT * FROM "
								+ " notifications as ntf"
								+ " LEFT JOIN notification_list as list "
								+ " ON  ntf.targetId = list.groupId ",
						inputQueryParam);
		SQL_QUERY += " UNION ";
		SQL_QUERY += queryBuilder.getSimpleOrQueryFromMap(
				" SELECT * FROM "
						+ "	notification_approval as ntf_appr"
						+ " INNER JOIN notification_list as list "
						+ " ON ntf_appr.targetId = list.groupId ",
				inputQueryParam);

		MapSqlParameterSource queryParams = queryBuilder
				.getNamedQueryParametersFromMap(inputQueryParam);
		System.out.println(SQL_QUERY + queryParams);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				this.jdbcTemplate);

		List<NotificationModel> notifications = template.query(SQL_QUERY,
				queryParams,new NotificationModelRowMapper());
		return notifications;
	}

	@Override
	public Boolean save(NotificationModel ntf,String tableName) {
	try {	
		List<String> columns = this.jdbcModelHelper.getColumns(ntf,FieldCacheType.ForInsert);
		
		Map<String,Object> objectMap = jdbcModelHelper.getDataMap(ntf, FieldCacheType.ForInsert);
		
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate);
			simpleJdbcInsert.setTableName(tableName);
			simpleJdbcInsert.setColumnNames(columns);
		int eventResponse = simpleJdbcInsert.execute(objectMap);
		System.out.println(eventResponse);
		if(eventResponse>0)
			return true;
		else
			throw new Exception("Error While Saving Notifications");
	}
	catch(Exception err){
		System.out.println(err.getMessage());
		return false;
		}
	}

	@Override
	public NotificationModel getNotificationById(String ntfId, int userId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("id", ntfId);
		String SQL_QUERY = "";
		SQL_QUERY += queryBuilder.getSimpleAndQueryFromMap(
				"select * from notifications", queryMap,true);
		SQL_QUERY += " UNION ";
		SQL_QUERY += queryBuilder.getSimpleAndQueryFromMap(
				"select * from notification_approval", queryMap,true);
		MapSqlParameterSource queryParam = queryBuilder
				.getNamedQueryParametersFromMap(queryMap);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				this.jdbcTemplate);

		List<NotificationModel> result = template.query(SQL_QUERY, queryParam,new NotificationModelRowMapper());
		return result.get(0);
	}

	@Override
	public Boolean updateNotificationById(Map<String, Object> body,
			String tableName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

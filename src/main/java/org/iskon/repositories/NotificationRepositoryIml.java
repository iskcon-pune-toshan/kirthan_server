package org.iskon.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.iskon.models.NotificationApprovalModel;
import org.iskon.models.NotificationModel;
import org.iskon.models.NotificationTrackerModel;
import org.iskon.models.UserTokenModel;
import org.iskon.utils.FieldCacheType;
import org.iskon.utils.JdbcModelHelper;
import org.iskon.utils.NotificationApprovalModelRowMapper;
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
		this.jdbcModelHelper.prepareObject(NotificationApprovalModel.class);
		this.jdbcModelHelper.prepareObject(UserTokenModel.class);
		this.jdbcModelHelper.prepareObject(NotificationTrackerModel.class);
	}

	@Override
	public Map<String,Object> getAll(int userId) {
		// Need to re to adjust for fetching notifications for a userId
		// 1]Find all the events he is in.
		// 2]Find all the teams he is in.
		// 3]Find if he is in any admin group.
		
		Map<String, Object> inputQueryParam = new HashMap<>();
		inputQueryParam.put("userId", userId);
		MapSqlParameterSource queryParams = queryBuilder
				.getNamedQueryParametersFromMap(inputQueryParam);
		System.out.println(queryParams);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				this.jdbcTemplate);
		
		String SQL_QUERY =
						"SELECT ntf.id,ntf.message,ntf.type,ntf.targetId,ntf.createdBy,ntf.createdAt FROM " + " notifications as ntf "
								+ " LEFT JOIN notification_tracker as ntf_trk "
								+ "	ON ntf.id = ntf_trk.ntfId"
								+ " where ntf_trk.userId = :userid "
								+ " ORDER BY ntf.createdAt DESC";
		
		List<NotificationModel> notifications = template.query(SQL_QUERY,
				queryParams, new NotificationModelRowMapper());
		SQL_QUERY =
				"SELECT ntf.id,ntf.message,ntF.type,ntf.targetId,ntf.createdBy,ntf.createdAt,ntf.action,ntf.mappingTableData FROM " + " notification_approval as ntf "
						+ " LEFT JOIN notification_tracker as ntf_trk "
						+ "	ON ntf.id = ntf_trk.ntfId"
						+ " where ntf_trk.userId = :userid"
						+ " ORDER BY ntf.createdAt DESC ";
		List<NotificationApprovalModel> notification_appr = template.query(SQL_QUERY,
				queryParams, new NotificationApprovalModelRowMapper());
		Map<String,Object> result = new HashMap<>();
		result.put("ntf",notifications);
		result.put("ntf_appr",notification_appr);
		
		return result;
	}

	@Override
	public List<Integer> getParticipants(int eventId){
		String userQuery = "SELECT DISTINCT userId from event_user_mapping where eventId = ?";
		List<Integer> finalData = new ArrayList<>();
		Set<Integer> uniqueId = new HashSet<Integer>() ;
		uniqueId.addAll( this.jdbcTemplate.query(userQuery,new Object[] {eventId},(rs,rowNumber)->{
			return rs.getInt("userId");
		}));	
		String teamQuery = "SELECT DISTINCT userId from team_user_mapping where teamId in "
				+ "				(SELECT DISTINCT teamId from event_user_mapping where eventID = ?)";
		uniqueId.addAll(this.jdbcTemplate.query(teamQuery, new Object[] {eventId}, 
				(rs,rowNumber)->{
					return rs.getInt("userId");
				}));
		//needs change
		String adminQuery = "SELECT DISTINCT id from user_request where city = ? and usertype = ?";
		uniqueId.addAll(this.jdbcTemplate.query(adminQuery,new Object[] {"Pune","admin"},(rs,rowNumber)->{
			return rs.getInt("id");
		}));
		finalData.addAll(uniqueId);
		return finalData;
	}
	
	@Override
	public List<Integer> getTeamMemberId(List<Integer> teamId){
		MapSqlParameterSource params = new MapSqlParameterSource("teamIds",teamId);
		String teamMemberQuery = "SELECT DISTINCT userId from team_user_mapping where teamId IN (:teamIds)";		
		NamedParameterJdbcTemplate template = new  NamedParameterJdbcTemplate(this.jdbcTemplate);
		List<Integer> teamUserId = template.query(teamMemberQuery, params, (rs,rowNumber)->{
			return rs.getInt("userId");
		});
		return teamUserId;
	}
	@Override
	public List<Integer> getTempleAdminId(int templeId){
		String role = "Local Admin";
		String query = " SELECT DISTINCT user_id from user_temple_mapping where id = ? and role = ?";	
		return this.jdbcTemplate.query(query,new Object[] {templeId,role},
				(rs,rowNumber)->{
					return rs.getInt("userId");
				});
	}
	
	@Override
	public Map<String,Object> getNotificationById(
			String ntfId, 
			int userId) {
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("id", ntfId);
		MapSqlParameterSource queryParam = queryBuilder
				.getNamedQueryParametersFromMap(queryMap);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				this.jdbcTemplate);
		
		String SQL_QUERY = queryBuilder.getSimpleAndQueryFromMap(
				"select * from notifications", queryMap, true);
		List<NotificationModel> result = template.query(SQL_QUERY, queryParam,
				new NotificationModelRowMapper());
		
		SQL_QUERY = queryBuilder.getSimpleAndQueryFromMap(
				"select * from notification_approval", queryMap, true);
		List<NotificationApprovalModel> resultAppr = template.query(SQL_QUERY, queryParam,
				new NotificationApprovalModelRowMapper());		
		Map<String, Object> returnData = new HashMap<>();
		if(result.size()==0 && resultAppr.size() == 0)
			returnData.put("ntf","No notification found.Incorrect Id");
		else if(result.size()==0)
			returnData.put("ntf_appr",resultAppr.get(0));
		else
			returnData.put("ntf",result.get(0));
		return returnData;
	}
	
	//these are notifcations table specific
	@Override
	public Boolean save(NotificationModel ntf, String tableName) {
		try {
			List<String> columns = 
					this.jdbcModelHelper.getColumns(ntf,
							FieldCacheType.ForInsert);

			Map<String, Object> objectMap = this.jdbcModelHelper.getDataMap(ntf,
					FieldCacheType.ForInsert);

			SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(
					this.jdbcTemplate);
			simpleJdbcInsert.setTableName(tableName);
			simpleJdbcInsert.setColumnNames(columns);
			int eventResponse = simpleJdbcInsert.execute(objectMap);
			if (eventResponse > 0)
				return true;
			else
				throw new Exception("Error While Saving Notifications");
		} 
		catch (Exception err) {
			System.out.println("User generated Exception : "+err.getMessage());
			return false;
		}
	}
	
	
	
	
	//this operates on Notification_tracker database
	@Override
	public Boolean saveNotificationTracker(NotificationTrackerModel ntfTracker) {
	try {	
		List<String> columns = this.jdbcModelHelper.getColumns(ntfTracker, FieldCacheType.ForInsert);
		Map<String,Object> queryInput =  this.jdbcModelHelper.getDataMap(ntfTracker, FieldCacheType.ForInsert);
		SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate);
		insert.setTableName("notification_tracker");
		insert.setColumnNames(columns);
		insert.setGeneratedKeyName("Id");
		Number result = insert.executeAndReturnKey(queryInput);
		ntfTracker.setId(result.intValue());
	}
	catch(Exception err) {
		System.out.println("Error at NotificationRepository@158"+ err.getMessage());
		return false;
	}
		return true;
	}
	//these operate on NotificationApproval
	@Override
	public Boolean save(NotificationApprovalModel ntf, String tableName) {
		try {
			List<String> columns = 
					this.jdbcModelHelper.getColumns(ntf,
							FieldCacheType.ForInsert);

			Map<String, Object> objectMap = this.jdbcModelHelper.getDataMap(ntf,
					FieldCacheType.ForInsert);

			SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(
					this.jdbcTemplate);
			simpleJdbcInsert.setTableName(tableName);
			simpleJdbcInsert.setColumnNames(columns);
			int eventResponse = simpleJdbcInsert.execute(objectMap);
			if (eventResponse > 0)
				return true;
			else
				throw new Exception("Error While Saving Notifications");
		} 
		catch (Exception err) {
			System.out.println("User generated Exception : "+err.getMessage());
			return false;
		}
	}
	
	
	@Override
	public NotificationApprovalModel updateNotificationById(Map<String, Object> body) {
	try {
		String response = "Rejected";
		if( body.get("response").equals(1)) response= "Approved";
		String updateQuery = "update notification_approval set action = ? where id = ?";
		int resp = this.jdbcTemplate.update(updateQuery,response,body.get("ntfId"));
		NotificationApprovalModel updatedNtf;
		if(resp==1)
			updatedNtf = (this.jdbcTemplate.query("SELECT * from notification_approval where id = ?",
							new Object[] {body.get("ntfId")},
							new NotificationApprovalModelRowMapper())).get(0);
		else
			updatedNtf = null;
		return updatedNtf;
	}
	catch(Exception err) {
		System.out.println(err.getMessage());
		return null;
	}
	}

}

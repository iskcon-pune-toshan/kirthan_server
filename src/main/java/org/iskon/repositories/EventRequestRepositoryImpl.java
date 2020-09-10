package org.iskon.repositories;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iskon.models.EventRequest;
import org.iskon.utils.EventRequestRowMapper;
import org.iskon.utils.FieldCacheType;
import org.iskon.utils.JdbcModelHelper;
import org.iskon.utils.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

//import org.springframework.data.convert.

@Component
public class EventRequestRepositoryImpl implements EventRequestRepository {

	private static String TableName = "event_request";
	@Autowired
	JdbcTemplate jdbcTemplate;

	//@Autowired
	JdbcModelHelper jdbcModelHelper;

	@Autowired
	QueryBuilder queryBuilder;

	@Autowired
	public EventRequestRepositoryImpl(JdbcModelHelper jdbcModelHelper) {
		this.jdbcModelHelper = jdbcModelHelper;
		this.jdbcModelHelper.prepareObject(EventRequest.class);
	}

	@Override
	public EventRequest submitNewEventRequest(EventRequest newEventRequest) {
		newEventRequest.setApprovalStatus("NEW");
		
		List<String> columns = jdbcModelHelper.getColumns(newEventRequest, FieldCacheType.ForInsert);
		Map<String, Object> objectMap = jdbcModelHelper.getDataMap(newEventRequest, FieldCacheType.ForInsert);

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.setTableName(TableName);
		simpleJdbcInsert.setColumnNames(columns);
		simpleJdbcInsert.setGeneratedKeyName("Id");

		Number eventId = simpleJdbcInsert.executeAndReturnKey(objectMap);
		newEventRequest.setId(eventId.intValue());

		return newEventRequest;
	}
	
	@Override
	public EventRequest submitUpdateEventRequest(EventRequest newEventRequest) {
		//newEventRequest.setApprovalStatus("UPDATE");
		
		Map.Entry<String, Object[]> kvpOfQueryAndArgs = queryBuilder.getUpdateEntryData(TableName, newEventRequest);

		int rows = jdbcTemplate.update(kvpOfQueryAndArgs.getKey(), kvpOfQueryAndArgs.getValue());

		System.out.println("rows updated: " + rows);

		return newEventRequest;
	}

	@Override
	public EventRequest submitDeleteEventRequest(EventRequest newEventRequest) {

		int rows = jdbcTemplate.update("delete from event_request where id = ?", newEventRequest.getId());

		System.out.println("rows deleted: " + rows);

		return newEventRequest;
	}
	
	public List<Integer> getEventRequestsCountByStatus(){
		ArrayList<Integer> data = new ArrayList<>();
		System.out.println("I was here");
		ArrayList<String> status = new ArrayList<>();
		status.add("Approved");
		status.add("NEW");
		status.add("Rejected");
		String sql;
		for(String i : status) {
			sql = String.format("Select count(id) from event_request where approvalstatus = '%s'", i);
			data.add(this.jdbcTemplate.query(sql,(rs)->{
				rs.next();
				return (rs.getInt("count(id)"));
			}));	
		}
		
		
			return data;
	}
	@Override
	public List<EventRequest> getEventRequests(Map<String, Object> queryMap) {
		//queryMap.clear();
		//queryMap.put("approvalStatus","Approved");
		
		String query = queryBuilder.getSimpleAndQueryFromMap("select * from event_request", queryMap,true);
		System.out.print(query);	
		MapSqlParameterSource queryParams = queryBuilder.getNamedQueryParametersFromMap(queryMap);
		System.out.print(queryParams);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<EventRequest> eventRequests = template.query(query, queryParams, new EventRequestRowMapper());
		System.out.println(eventRequests);
		return eventRequests;
	}
	
	@Override
	public Boolean processEventRequest(Integer id, String approvalstatus, String approvalcomments,
			String usertype, String updatedby) {
		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
	            .withProcedureName("process_event_request")
	            . declareParameters(
	                    new SqlParameter("eventid", Types.INTEGER),
	                    new SqlParameter("approvalstatus", Types.VARCHAR),
	                    new SqlParameter("approvalcomments", Types.VARCHAR),
	                    new SqlParameter("usertype", Types.VARCHAR),
	                    new SqlParameter("updatedby", Types.VARCHAR),
	                    new SqlParameter("updatetime", Types.TIMESTAMP),
	                    new SqlOutParameter("operationstatus", Types.BOOLEAN),
	                    new SqlOutParameter("errormessage", Types.VARCHAR));

		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("eventid", id);
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

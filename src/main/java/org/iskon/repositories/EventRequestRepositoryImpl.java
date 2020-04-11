package org.iskon.repositories;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.iskon.models.EventRequest;
import org.iskon.utils.QueryBuilder;
import org.iskon.utils.EventRequestRowMapper;
import org.iskon.utils.FieldCacheType;
import org.iskon.utils.JdbcModelHelper;

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
public class EventRequestRepositoryImpl implements EventRequestRepository {

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
		simpleJdbcInsert.setTableName("event_request");
		simpleJdbcInsert.setColumnNames(columns);
		simpleJdbcInsert.setGeneratedKeyName("eventId");

		Number eventId = simpleJdbcInsert.executeAndReturnKey(objectMap);
		newEventRequest.setEventId(eventId.intValue());

		return newEventRequest;
	}

	@Override
	public List<EventRequest> getEventRequests(Map<String, Object> queryMap) {

		String query = queryBuilder.getSimpleAndQueryFromMap("select * from event_request", queryMap);

		MapSqlParameterSource queryParams = queryBuilder.getNamedQueryParametersFromMap(queryMap);

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

		List<EventRequest> eventRequests = template.query(query, queryParams, new EventRequestRowMapper());

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

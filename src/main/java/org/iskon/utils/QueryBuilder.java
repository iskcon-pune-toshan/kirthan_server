package org.iskon.utils;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.iskon.models.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class QueryBuilder {

	JdbcModelHelper jdbcModelHelper;

	@Autowired
	public QueryBuilder(JdbcModelHelper jdbcModelHelper) {
		this.jdbcModelHelper = jdbcModelHelper;
	}

	public String getSimpleAndQueryFromMap(String baseQuery, Map<String, Object> arguments) {

		if (arguments.isEmpty())
			return baseQuery;

		int mapSize = arguments.size();

		String query = baseQuery + " where ";

		for (Entry<String, Object> kvp : arguments.entrySet()) {
			String lowercasedStr = kvp.getKey().toLowerCase();
			query = query + lowercasedStr + "=:" + lowercasedStr;
			mapSize--;

			if (mapSize > 0)
				query = query + " AND ";
		}

		return query;
	}

	public MapSqlParameterSource getNamedQueryParametersFromMap(Map<String, Object> queryMap) {
		MapSqlParameterSource queryParams = new MapSqlParameterSource();

		for (Entry<String, Object> kvp : queryMap.entrySet()) {
			queryParams.addValue(kvp.getKey().toLowerCase(), kvp.getValue());
		}
		return queryParams;
	}

	public Map.Entry<String, Object[]> getUpdateEntryData(String tableName, BaseModel obj) {
		
		Map<String, Object> queryMap = jdbcModelHelper.getDataMap(obj, FieldCacheType.ForUpdate);
		if (queryMap.isEmpty())
			throw new RuntimeException("No Updatable fields found in the Model");

		List params = new ArrayList();
		String columnTagsQuery = getQueryPart(queryMap, params, "?");
		
		queryMap = jdbcModelHelper.getDataMap(obj, FieldCacheType.Keys);
		if (queryMap.isEmpty())
			throw new RuntimeException(
					"No Key fields found in the Model. Hence Update should not proceed as this will update all the rows.");

		String whereClause = getQueryPart(queryMap, params, " AND ");
		
		String query = "UPDATE " + tableName + " SET " + columnTagsQuery + " WHERE" + whereClause;
		Map.Entry<String, Object[]> kvp = new AbstractMap.SimpleImmutableEntry<String, Object[]>(query,params.toArray());
		return kvp;
	}

	private String getQueryPart(Map<String, Object> queryMap, List params, String separator) {
		String setDataTag = " ";
		for (Entry<String, Object> kvp : queryMap.entrySet()) {
			String key = kvp.getKey().toLowerCase();
			setDataTag = setDataTag + key + " = ?" + separator;
			params.add(kvp.getValue());
		}
		//TODO: Ugly, find a better way to do this
		return setDataTag.substring(0, setDataTag.length() - separator.length());
	}
}

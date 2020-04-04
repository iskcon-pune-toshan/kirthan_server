package org.iskon.utils;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class QueryBuilder {
	
	public String getSimpleAndQueryFromMap(String baseQuery, Map<String,Object> arguments) {
		
		if(arguments.isEmpty())
			return baseQuery;
		
		int mapSize = arguments.size();
		
		String query = baseQuery + " where ";
		
		for (Entry<String, Object> kvp : arguments.entrySet()) {
			String lowercasedStr = kvp.getKey().toLowerCase();
			query = query + lowercasedStr + "=:" + lowercasedStr;
			mapSize--;
			
			if(mapSize > 0)
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

	
}

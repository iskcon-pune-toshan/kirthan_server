package org.iskon.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.iskon.models.NotificationListModel;
import org.springframework.jdbc.core.RowMapper;

public class NotificationListModelRowMapper implements RowMapper<NotificationListModel> {

	@Override
	public NotificationListModel mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		Map<String,Object> data = new HashMap<>();
		data.put("groupId",rs.getInt("groupId"));
		data.put("userId",rs.getInt("userId"));
		data.put("userType",rs.getString("userType"));
		return new NotificationListModel((int)rs.getInt("id"),data);
	}

	
}

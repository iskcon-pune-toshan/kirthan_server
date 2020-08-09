package org.iskon.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.iskon.models.NotificationModel;
import org.springframework.jdbc.core.RowMapper;
public class NotificationModelRowMapper implements RowMapper<NotificationModel>{

	@Override
	public NotificationModel mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		Map<String,Object> data = new HashMap<>();
		data.put("message",rs.getString("message"));
		data.put("type",rs.getString("type"));
		data.put("targetId",rs.getInt("targetId"));
		data.put("createdBy",rs.getInt("createdBy"));
		data.put("createdAt",rs.getTimestamp("createdAt").toLocalDateTime());
		return new NotificationModel(
				UUID.fromString(rs.getString("id")),data);
	}
 
}

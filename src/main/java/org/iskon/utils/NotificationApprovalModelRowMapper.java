package org.iskon.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.iskon.models.NotificationApprovalModel;
import org.iskon.models.NotificationModel;
import org.springframework.jdbc.core.RowMapper;
public class NotificationApprovalModelRowMapper implements RowMapper<NotificationApprovalModel>{

	@Override
	public NotificationApprovalModel mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		Map<String,Object> data = new HashMap<>();
		data.put("message",rs.getObject("message"));
		data.put("type",rs.getObject("type"));
		data.put("targetId",rs.getObject("targetId"));
		data.put("createdBy",rs.getObject("createdBy"));
		data.put("createdAt",rs.getTimestamp("createdAt").toLocalDateTime());
		data.put("action",rs.getObject("action"));
		data.put("mappingTableData",rs.getObject("mappingTableData"));
		return new NotificationApprovalModel(UUID.fromString(rs.getString("id")),data);
	}
 
}



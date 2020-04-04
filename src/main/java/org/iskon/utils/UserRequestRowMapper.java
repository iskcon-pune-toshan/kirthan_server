package org.iskon.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.iskon.models.UserRequest;
import org.springframework.jdbc.core.RowMapper;


public class UserRequestRowMapper implements RowMapper<UserRequest> {

	@Override
	public UserRequest mapRow(ResultSet rs, int rowNum) throws SQLException {

		UserRequest ur = new UserRequest();
		ur.setId(rs.getInt("id"));
		ur.setFirstName(rs.getString("firstname"));
		ur.setLastName(rs.getString("lastname"));
		ur.setEmail(rs.getString("email"));
		ur.setUserName(rs.getString("username"));
		ur.setPassword(rs.getString("password"));
		ur.setPhoneNumber(rs.getLong("phoneNumber"));
		ur.setAddLineOne(rs.getString("addlineone"));
		ur.setAddLineTwo(rs.getString("addlinetwo"));
		ur.setAddLineThree(rs.getString("addlinethree"));
		ur.setLocality(rs.getString("locality"));
		ur.setCity(rs.getString("city"));
		ur.setPinCode(rs.getInt("pincode"));
		ur.setState(rs.getString("state"));
		ur.setCountry(rs.getString("country"));
		ur.setGovtIdType(rs.getString("govtidtype"));
		ur.setGovtId(rs.getString("govtid"));
		ur.setIsProcessed(rs.getBoolean("isprocessed"));
		ur.setCreatedBy(rs.getString("createdby"));
		ur.setUpdatedBy(rs.getString("updatedby"));
		ur.setCreateTime(rs.getDate("createtime"));
		ur.setUpdateTime(rs.getDate("updatetime"));
		ur.setApprovalStatus(rs.getString("approvalstatus"));
		ur.setApprovalComments(rs.getString("approvalcomments"));
		ur.setUserId(rs.getString("userid"));
		ur.setUserType(rs.getString("usertype"));
		return ur;
	}

}

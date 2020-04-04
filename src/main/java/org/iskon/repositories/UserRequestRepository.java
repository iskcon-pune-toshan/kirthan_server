package org.iskon.repositories;

import java.util.List;
import java.util.Map;

import org.iskon.models.UserRequest;

public interface UserRequestRepository {

	UserRequest submitNewUserRequest(UserRequest newUserRequest);
	
	UserRequest submitUpdateUserRequest(UserRequest newUserRequest);
	
	UserRequest submitDeleteUserRequest(UserRequest newUserRequest);
	
	List<UserRequest> getUserRequests(Map<String,Object> query);
	
	Boolean processUserRequest(Integer id, String approvalstatus, String approvalcomments, String usertype, String updatedby);
}

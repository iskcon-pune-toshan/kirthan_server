package org.iskon.services;

import java.util.List;
import java.util.Map;

import org.iskon.models.UserRequest;

public interface UserRequestService {
	
	UserRequest submitNewUserRequest(UserRequest userRequest);
	
	UserRequest submitUpdateUserRequest(UserRequest userRequest);
	
	UserRequest submitDeleteUserRequest(UserRequest userRequest);
	
	List<UserRequest> getUserRequests(Map<String,Object> query);
	
	Boolean processUserRequest(Map<String,Object> params);
}

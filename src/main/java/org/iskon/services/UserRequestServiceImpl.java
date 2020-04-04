package org.iskon.services;

import java.util.List;
import java.util.Map;

import org.iskon.models.UserRequest;
import org.iskon.repositories.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRequestServiceImpl implements UserRequestService {

	UserRequestRepository userRequestRepository;
	
	@Autowired
	public UserRequestServiceImpl(UserRequestRepository userRequestRepository)
	{
		this.userRequestRepository = userRequestRepository;
	}

	@Override
	public UserRequest submitNewUserRequest(UserRequest userRequest) 
	{
		return this.userRequestRepository.submitNewUserRequest(userRequest);
	}
	
	@Override
	public UserRequest submitUpdateUserRequest(UserRequest userRequest) 
	{
		return this.userRequestRepository.submitUpdateUserRequest(userRequest);
	}

	@Override
	public UserRequest submitDeleteUserRequest(UserRequest userRequest) 
	{
		return this.userRequestRepository.submitDeleteUserRequest(userRequest);
	}
	

	@Override
	public List<UserRequest> getUserRequests(Map<String, Object> query) {
		
		return this.userRequestRepository.getUserRequests(query);
	}

	@Override
	public Boolean processUserRequest(Map<String,Object> params) {
		// validate input
		// Obtain user from current principal after security is implemented
		Boolean result = this.userRequestRepository.processUserRequest((Integer)params.get("id"), 
				(String)params.get("approvalstatus"), (String)params.get("approvalcomments"), (String)params.get("usertype"), "Srinivas");
		if(result)
		{
			// Send Email
		}
		
		return result;
	}
}

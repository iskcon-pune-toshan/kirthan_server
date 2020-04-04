package org.iskon.controllers;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.iskon.models.UserRequest;
//import org.iskon.models.UserRequests;
import org.iskon.services.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserRequestController {

	@Autowired
	UserRequestService userRequestService;
	
	@RequestMapping(value = "/getdummyuserrequest", method = RequestMethod.GET)
	public List<UserRequest> getDummyUserRequest() { 		
		List<UserRequest> userreqs = new ArrayList<UserRequest>();
		UserRequest req = getDummyUserRequestObj();	
		userreqs.add(req);
		//userreqs.add(req);
		return userreqs;
	}
	
	@RequestMapping(value = "/submitnewuserrequest", method = RequestMethod.PUT)
	public UserRequest submitNewUserRequest(@RequestBody UserRequest newUserRequest) {
		System.out.println(newUserRequest);
		UserRequest req = userRequestService.submitNewUserRequest(newUserRequest);
		return req;
	}
	
	@RequestMapping(value = "/submitupdateuserrequest", method = RequestMethod.PUT)
	public UserRequest submitUpdateUserRequest(@RequestBody UserRequest newUserRequest) {
		System.out.println(newUserRequest);
		UserRequest req = userRequestService.submitUpdateUserRequest(newUserRequest);
		return req;
	}
	
	@RequestMapping(value = "/submitdeleteuserrequest", method = RequestMethod.PUT)
	public UserRequest submitDeleteUserRequest(@RequestBody UserRequest newUserRequest) {
		System.out.println(newUserRequest);
		UserRequest req = userRequestService.submitDeleteUserRequest(newUserRequest);
		return req;
	}	
	
	
	@RequestMapping(value = "/getuserrequests", method = RequestMethod.PUT)
	public List<UserRequest> getUserRequests(@RequestBody Map<String,Object> queryParams) {
		//System.out.println("queryParams: "+queryParams);
		List<UserRequest> req = userRequestService.getUserRequests(queryParams);
		return req;
	}
	
	@RequestMapping(value = "/processuserrequest", method = RequestMethod.PUT)
	public Boolean processUserRequest(@RequestBody Map<String,Object> params) {
		System.out.println(params);
		Boolean req = userRequestService.processUserRequest(params);
		return req;
	}
	
	private UserRequest getDummyUserRequestObj()
	{
		UserRequest ur = new UserRequest();
		ur.setFirstName("Srinivas");
		ur.setLastName("Naik");
		ur.setEmail("srinivasvn84@gmail.com");
		ur.setUserName("srinivasvnaik");
		ur.setPassword("password123");
		ur.setPhoneNumber(8007774787L);
		ur.setAddLineOne("Flat no 20, Kalyani A");
		ur.setAddLineTwo("Aditya Garden City");
		ur.setLocality("Warje");
		ur.setCity("Pune");
		ur.setState("Maharastra");
		ur.setCountry("India");
		ur.setPinCode(411058);
		ur.setGovtIdType("PAN");
		ur.setGovtId("AEZPN699F");
		ur.setIsProcessed(false);
		ur.setCreatedBy("Srinivas");
		ur.setCreateTime(new Date());
		ur.setUserId("Srinivasa_naik");
		ur.setUserType("SuperAdmin");
		ur.setId(1);
		ur.setUpdatedBy("Manjunath");
		ur.setUpdateTime(new Date());
		ur.setAddLineThree("Add Line three");
		ur.setApprovalStatus("Draft");
		return ur;
	}
}

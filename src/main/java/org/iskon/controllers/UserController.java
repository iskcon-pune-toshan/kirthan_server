package org.iskon.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.iskon.models.User;
import org.iskon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.iskon.models.UserSearch;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/getdummyuser", method = RequestMethod.GET)
	public List<User> getDummyUser() {
		List<User> userreqs = new ArrayList<User>();
		User req = getDummyUserObj();
		userreqs.add(req);
		return userreqs;
	}

	@RequestMapping(value = "/adduser", method = RequestMethod.PUT)
	public User addUser(@RequestBody User newUser) {
		System.out.println(newUser);
		User req = userService.addUser(newUser);
		NotificationWrapper nw = new NotificationWrapper();
		nw.generateNotification(req);
		return req;
	}

	@RequestMapping(value = "/updateuser", method = RequestMethod.PUT)
	public User updateUser(@RequestBody User newUser) {
		System.out.println(newUser);
		User req = userService.updateUser(newUser);
		NotificationWrapper nw = new NotificationWrapper();
		nw.generateNotification(req);
		return req;
	}

	@RequestMapping(value = "/deleteuser", method = RequestMethod.PUT)
	public void deleteUser(@RequestBody User newUser) {
		System.out.println(newUser);
		userService.deleteUser(newUser);
//		NotificationWrapper nw = new NotificationWrapper();
//		nw.populateUserNotification(req);
		
	}


	@RequestMapping(value = "/getuser", method = RequestMethod.PUT)
	public List<User> getUser(@RequestBody UserSearch queryParams) {
		//System.out.println("queryParams: "+queryParams);
		List<User> req = userService.getUsers(queryParams);
		return req;
	}

	@RequestMapping(value = "/processuser", method = RequestMethod.PUT)
	public Boolean processUser(@RequestBody User params) {
		System.out.println(params);
		Boolean req = userService.processUser(params);
		return req;
	}

	private User getDummyUserObj()
	{
		User ur = User.buildUser(1, "Srinivas","Naik", "srinivasvn8@gmail.com", "srinivasvnaik",
				"password1234", 8007774787L, "Flat No 20, Kalyani A", "Aditya GArden City",
				"","Warje", "Pune", 411058,"Mashrashtra", "India", "PAN",
				"AEZPN699F", false, "Draft", "", 1, "Srinivas",
				null, new Date(), new Date());
//		ur.setFirstName("Srinivas");
//		ur.setLastName("Naik");
//		ur.setEmail("srinivasvn84@gmail.com");
//		ur.setUserName("srinivasvnaik");
//		ur.setPassword("password123");
//		ur.setPhoneNumber(8007774787L);
//		ur.setAddLineOne("Flat no 20, Kalyani A");
//		ur.setAddLineTwo("Aditya Garden City");
//		ur.setLocality("Warje");
//		ur.setCity("Pune");
//		ur.setState("Maharastra");
//		ur.setCountry("India");
//		ur.setPinCode(411058);
//		ur.setGovtIdType("PAN");
//		ur.setGovtId("AEZPN699F");
//		ur.setIsProcessed(false);
//		ur.setCreatedBy("Srinivas");
//		ur.setCreatedTime(new Date());
//		ur.setRoleId(1);
//		ur.setId(1);
//		ur.setUpdatedBy("Manjunath");
//		ur.setUpdatedTime(new Date());
//		ur.setAddLineThree("Add Line three");
//		ur.setApprovalStatus("Draft");
		return ur;
	}
}

package org.iskon.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.iskon.authentication.JwtUtil;
import org.iskon.models.Preferences;
import org.iskon.models.User;
import org.iskon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.iskon.models.UserSearch;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private NotificationWrapper nw;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PreferencesController pref;

	
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
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		System.out.println("User after encrypted password :" + newUser.getPassword());
		User req = userService.addUser(newUser);
		nw.generateNotification(req, "single");
		//pref.getUserId(req);
		return req;
	}

	@RequestMapping(value = "/updateuser", method = RequestMethod.PUT)
	public User updateUser(@RequestBody User newUser) {
		System.out.println("In update service");
		System.out.println(newUser);
		User req = userService.updateUser(newUser);
		//NotificationWrapper nw = new NotificationWrapper();
		if(req.getRoleId() == 2 )
			nw.generateNotification(req, "make local admin");
		else if(req.getRoleId() == 3)
			nw.generateNotification(req, "make user");
		//pref.getUserId(req);
		return req;
	}
	
	@RequestMapping(value = "/updateuserdetails", method = RequestMethod.PUT)
	public User updateUserDetails(@RequestBody User newUser) {
		System.out.println(newUser);
		try {
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		System.out.println("User after encrypted password :" + newUser.getPassword());}
		catch(NullPointerException npe) {
			System.out.println("Password not updated");
		}
		User req = userService.updateUser(newUser);
		return req;
	}
	
	@RequestMapping(value = "/initiateteam", method = RequestMethod.PUT)
	public User initiateTeam(@RequestBody User newUser) {
		System.out.println(newUser);
		User req = userService.updateUser(newUser); //saves user
		nw.generateNotification(req, "initiate team");
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
				"AEZPN699F", false, "Draft", "", 1, 1, "Srinivas",
				null, new Date(), new Date(), null);
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

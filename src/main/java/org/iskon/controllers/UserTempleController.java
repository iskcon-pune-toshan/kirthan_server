package org.iskon.controllers;

import org.iskon.models.UserTemple;
import org.iskon.models.UserTempleSearch;
import org.iskon.services.UserTempleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/usertemple")
public class UserTempleController {

	@Autowired
	private UserTempleService usertempleService;
	
	@PutMapping("/addusertemple")
	public UserTemple addTemple(@RequestBody UserTemple usertemple) {
		UserTemple req = usertempleService.addUserTemple(usertemple);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(req);*/
		return req;
	}
	
	@PutMapping("/updateusertemple")
	public UserTemple updateTemple(@RequestBody UserTemple temple) {
		System.out.println(temple);
		UserTemple req = usertempleService.updateUserTemple(temple);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(req);*/
		
		return req;
	}
	
	@PutMapping("/deleteusertemple")
	public void deleteEvent(@RequestBody UserTemple temple) {
		System.out.println(temple);
		usertempleService.deleteUserTemple(temple);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(newEvent);*/
	}
	
	@PutMapping("/getusertemple")
	public List<UserTemple> getTemple(@RequestBody UserTempleSearch templeSearch) {
		List<UserTemple> req = usertempleService.getUserTemple(templeSearch);
		return req;
	}
	

}

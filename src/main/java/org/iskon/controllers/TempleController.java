package org.iskon.controllers;

import org.iskon.models.Temple;
import org.iskon.models.TempleSearch;
import org.iskon.services.TempleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/temple")
public class TempleController {

	@Autowired
	private TempleService templeService;
	
	@PutMapping("/addtemple")
	public Temple addTemple(@RequestBody Temple temple) {
		Temple req = templeService.addTemple(temple);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(req);*/
		return req;
	}
	
	@PutMapping("/updatetemple")
	public Temple updateTemple(@RequestBody Temple temple) {
		System.out.println(temple);
		Temple req = templeService.updateTemple(temple);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(req);*/
		
		return req;
	}
	
	@PutMapping("/deletetemple")
	public void deleteEvent(@RequestBody Temple temple) {
		System.out.println(temple);
		templeService.deleteTemple(temple);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(newEvent);*/
	}
	
	@PutMapping("/gettemple")
	public List<Temple> getTemple(@RequestBody TempleSearch templeSearch) {
		List<Temple> req = templeService.getTemple(templeSearch);
		return req;
	}
	

}

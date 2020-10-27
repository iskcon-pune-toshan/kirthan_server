package org.iskon.controllers;

import org.iskon.models.Screens;
import org.iskon.models.ScreensSearch;
import org.iskon.services.ScreensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/screen")
public class ScreensController {

	@Autowired
	private ScreensService screensService;
	
	@PutMapping("/addscreen")
	public Screens addScreens(@RequestBody Screens screen) {
		Screens req = screensService.addScreens(screen);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(req);*/
		return req;
	}
	
	@PutMapping("/updatescreen")
	public Screens updateScreens(@RequestBody Screens screen) {
		System.out.println(screen);
		Screens req = screensService.updateScreens(screen);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(req);*/
		
		return req;
	}
	
	@PutMapping("/deletescreen")
	public void deleteScreens(@RequestBody Screens screen) {
		System.out.println(screen);
		screensService.deleteScreens(screen);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(newEvent);*/
	}
	
	@PutMapping("/getscreen")
	public List<Screens> getTemple(@RequestBody ScreensSearch screenSearch) {
		List<Screens> req = screensService.getScreens(screenSearch);
		return req;
	}
	

}

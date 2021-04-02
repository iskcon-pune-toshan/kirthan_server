package org.iskon.controllers;

import org.iskon.authentication.JwtUtil;
import org.iskon.models.Event;
import org.iskon.models.Preferences;
import org.iskon.models.PreferencesSearch;
import org.iskon.models.User;
import org.iskon.models.UserSearch;
import org.iskon.models.UserTemple;
import org.iskon.models.EventSearch;
import org.iskon.models.NotificationUi;
import org.iskon.services.PreferencesService;
import org.iskon.services.UserService;
import org.iskon.utils.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
//import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/preferences")
public class PreferencesController {

	@Autowired
	private PreferencesService preferencesService;
	
	
	@GetMapping("/getdummyeventpref")
	public List<Preferences> getDummyPreferences() { 		
		List<Preferences> eventprefs = new ArrayList<Preferences>();
		Preferences req = getDummyPreferencesObj();	
		eventprefs.add(req);
		return eventprefs;
	}
	
	@PutMapping("/addeventpref")
	public Preferences addEventPreferences(@RequestBody Preferences newEvent) {
		Preferences req = preferencesService.addPreferences(newEvent);
		return req;
	}
	
	@PutMapping("/updateeventpref")
	public Preferences updateEventPreferences(@RequestBody Preferences newEvent) {
		System.out.println(newEvent);	
		Preferences req = preferencesService.updateEventPreferences(newEvent);
		return req;
	}
	
//	@PutMapping("/getprefduration")
//	public Integer getPreferenceDuration(@RequestBody PreferencesSearch eventPref) {
//		Integer req = preferencesService.getPreferenceDuration(eventPref);
//		return req;
//	}
	
	@RequestMapping("/geteventprefs")
	public List<Preferences> getEventPreferences(@RequestBody PreferencesSearch eventPref) {
		List<Preferences> req = preferencesService.getEventPreferences(eventPref);
		return req;
	}
	
//	@RequestMapping("/getUserId")
//	public Preferences getUserId(User user) {
//		Preferences pref = new Preferences();
//		pref.setUserId(user.getId().toString());
//		return addEventPreferences(pref);
//	}
	private String getJwt(String authorizationHeader) {
		JwtUtil jwtUtil = new JwtUtil();
		String jwt = authorizationHeader.replace("Bearer ", "");
		return jwtUtil.extractUsername(jwt);
		
	}
	@GetMapping(value = "/getpreferenceswithdescription")
	public List<Preferences> getPreferencesWithDescription(@RequestHeader("Authorization") String authHead) throws HttpException {
		return preferencesService.getPreferencesWithDescription(getJwt(authHead));
	}
	
//	@RequestMapping(value = "/getpreferenceswithdescription", method = RequestMethod.PUT)
//	public List<Preferences> getPreferencesWithDescription() {
//		 System.out.println("Preferences Controller");
//		List<Preferences> req = preferencesService.getPreferencesWithDescription();
//		System.out.println(req);
//		return req;
//	}

	
	private Preferences getDummyPreferencesObj()
	{
		Preferences er = Preferences.buildPreferences(null,"new@gmail.com", "NVCC", "1", 1 ,"One week before" , "1,2,3,4");
		return er;
	}

}

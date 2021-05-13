package org.iskon.controllers;

import org.iskon.models.ProspectiveUser;
import org.iskon.models.ProspectiveUserSearch;
import org.iskon.models.EventSearch;
import org.iskon.services.EventService;
import org.iskon.services.ProspectiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.threeten.bp.LocalDate;

import com.google.api.client.util.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/prospectiveuser")
public class ProspectiveUserController {

	@Autowired
	private NotificationWrapper ntfWrapper;
	
	@Autowired
	private NotificationController ntfc;
	
	@Autowired
	private ProspectiveUserService prospectiveUserService;
	
	@GetMapping("/getdummyprospectiveuser")
	public List<ProspectiveUser> getDummyEvent() { 		
		List<ProspectiveUser> eventreqs = new ArrayList<ProspectiveUser>();
		ProspectiveUser req = getDummyProspectiveUserObj();	
		eventreqs.add(req);
		return eventreqs;
	}
	
	@PutMapping("/addprospectiveuser")
	public ProspectiveUser addProspectiveUser(@RequestBody ProspectiveUser newEvent) {
		ProspectiveUser req = prospectiveUserService.addProspectiveUser(newEvent);
		return req;
	}
	

	
	@PutMapping("/updateprospectiveuser")
	public ProspectiveUser updateProspectiveUser(@RequestBody ProspectiveUser newEvent) {
		System.out.println(newEvent);	
		ProspectiveUser req = prospectiveUserService.updateProspectiveUser(newEvent);
		return req;
	}
	
	
	@PutMapping("/deleteprospectiveuser")
	public void deleteProspectiveUser(@RequestBody ProspectiveUser newEvent) {
		prospectiveUserService.deleteProspectiveUser(newEvent);
		
	}
	
	@PutMapping("/getprospectiveuser")
	public List<ProspectiveUser> getProspectiveUser(@RequestBody ProspectiveUserSearch prospectiveUserSearch) {
		List<ProspectiveUser> req = prospectiveUserService.getProspectiveUser(prospectiveUserSearch);
		return req;
	}
	
	@PutMapping("/processprospectiveuser")
	public Boolean processProspectiveUser(@RequestBody ProspectiveUser newEvent) {
		Boolean req = prospectiveUserService.processProspectiveUser(newEvent);
		return req;
	}
	
	
	private ProspectiveUser getDummyProspectiveUserObj()
	{
		ProspectiveUser er = ProspectiveUser.buildProspectiveUser(null, "trialuser123@gmail.com", "teehee@gmail.com", "local admin",
				"411014",false);
		return er;
	}

}

package org.iskon.controllers;

import org.iskon.models.RoleScreen;
import org.iskon.models.RoleScreenSearch;
import org.iskon.services.RoleScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/rolescreen")
public class RoleScreenController {

	@Autowired
	private RoleScreenService roleScreenService;
	
	@PutMapping("/addrolescreen")
	public RoleScreen addRoleScreen(@RequestBody RoleScreen rolecreen) {
		RoleScreen req = roleScreenService.addRoleScreen(rolecreen);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(req);*/
		return req;
	}
	
	@PutMapping("/updaterolescreen")
	public RoleScreen updateRoleScreen(@RequestBody RoleScreen Rolescreen) {
		System.out.println(Rolescreen);
		RoleScreen req = roleScreenService.updateRoleScreen(Rolescreen);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(req);*/
		
		return req;
	}
	
	@PutMapping("/deleterolescreen")
	public void deleteRoleScreen(@RequestBody RoleScreen rolescreen) {
		System.out.println(rolescreen);
		roleScreenService.deleteRoleScreen(rolescreen);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(newEvent);*/
	}
	
	@PutMapping("/getrolescreen")
	public List<RoleScreen> getRoles(@RequestBody RoleScreenSearch rolescreenSearch) {
		List<RoleScreen> req = roleScreenService.getRoleScreen(rolescreenSearch);
		return req;
	}
	

}

package org.iskon.controllers;

import org.iskon.models.Roles;
import org.iskon.models.RolesSearch;
import org.iskon.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

	@Autowired
	private RolesService roleService;
	
	@PutMapping("/addroles")
	public Roles addRoles(@RequestBody Roles role) {
		Roles req = roleService.addRoles(role);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(req);*/
		return req;
	}
	
	@PutMapping("/updateroles")
	public Roles updateRoles(@RequestBody Roles Roles) {
		System.out.println(Roles);
		Roles req = roleService.updateRoles(Roles);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(req);*/
		
		return req;
	}
	
	@PutMapping("/deleteroles")
	public void deleteRoles(@RequestBody Roles Roles) {
		System.out.println(Roles);
		roleService.deleteRoles(Roles);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(newEvent);*/
	}
	
	@PutMapping("/getroles")
	public List<Roles> getRoles(@RequestBody RolesSearch roleSearch) {
		List<Roles> req = roleService.getRoles(roleSearch);
		return req;
	}
	

}

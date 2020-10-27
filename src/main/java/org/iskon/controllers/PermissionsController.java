package org.iskon.controllers;

import org.iskon.models.Permissions;
import org.iskon.models.PermissionsSearch;
import org.iskon.services.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionsController {

	@Autowired
	private PermissionsService permissionsService;
	
	@PutMapping("/addpermissions")
	public Permissions addPermissions(@RequestBody Permissions permissions) {
		Permissions req = permissionsService.addPermissions(permissions);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(req);*/
		return req;
	}
	
	@PutMapping("/updatepermissions")
	public Permissions updatePermissions(@RequestBody Permissions permissions) {
		System.out.println(permissions);
		Permissions req = permissionsService.updatePermissions(permissions);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(req);*/
		
		return req;
	}
	
	@PutMapping("/deletepermissions")
	public void deletePermissions(@RequestBody Permissions permissions) {
		System.out.println(permissions);
		permissionsService.deletePermissions(permissions);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(newEvent);*/
	}
	
	@PutMapping("/getpermissions")
	public List<Permissions> getPermissions(@RequestBody PermissionsSearch permissionsSearch) {
		List<Permissions> req = permissionsService.getPermissions(permissionsSearch);
		return req;
	}
	

}

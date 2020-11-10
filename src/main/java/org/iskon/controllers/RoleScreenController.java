package org.iskon.controllers;

import org.iskon.models.RoleScreen;
import org.iskon.models.RoleScreenSearch;
import org.iskon.models.UserTemple;
import org.iskon.models.UserTempleSearch;
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

	@RequestMapping(value = "/addrolescreen", method = RequestMethod.PUT)
	public List<RoleScreen> addRoleScreen(@RequestBody List<RoleScreen> listRoleScreen) {
		List<RoleScreen> res = roleScreenService.addRoleScreen(listRoleScreen);

		return res;
	}

	@RequestMapping(value = "/getrolescreen", method = RequestMethod.PUT)
	public List<RoleScreen> getRoleScreen(@RequestBody RoleScreenSearch rolescreenSearch) {
		List<RoleScreen> req = roleScreenService.getRoleScreen(rolescreenSearch);
		return req;
	}

	@RequestMapping(value = "/deleterolescreen", method = RequestMethod.PUT)
	public List<RoleScreen> deleteRoleScreen(@RequestBody List<RoleScreen> listRoleScreen) {
		roleScreenService.deleteRoleScreen(listRoleScreen);

		return listRoleScreen;
	}
	
	@RequestMapping(value = "/getrolescreenwithdescription", method = RequestMethod.PUT)
	public List<RoleScreen> getRoleScreenWithDescription() {
		List<RoleScreen> req = roleScreenService.getRoleScreenWithDescription();
		return req;
	}


	

}

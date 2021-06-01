package org.iskon.controllers;

import org.iskon.models.CommonLookupTable;
import org.iskon.models.CommonLookupTableSearch;
import org.iskon.services.CommonLookupTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/commonlookuptable")
public class CommonLookupTableController {

	@Autowired
	private CommonLookupTableService cltService;
	
	@PutMapping("/addclt")
	public CommonLookupTable addCommonLookupTable(@RequestBody CommonLookupTable role) {
		CommonLookupTable req = cltService.addCommonLookupTable(role);
		return req;
	}
	
	@PutMapping("/updateclt")
	public CommonLookupTable updateCommonLookupTable(@RequestBody CommonLookupTable Roles) {
		CommonLookupTable req = cltService.updateCommonLookupTable(Roles);
		return req;
	}
	
	@PutMapping("/deleteclt")
	public void deleteCommonLookupTable(@RequestBody CommonLookupTable Roles) {
		cltService.deleteCommonLookupTable(Roles);
	}
	
	@PutMapping("/getclt")
	public List<CommonLookupTable> getRoles(@RequestBody CommonLookupTableSearch roleSearch) {
		List<CommonLookupTable> req = cltService.getCommonLookupTable(roleSearch);
		return req;
	}
	

}

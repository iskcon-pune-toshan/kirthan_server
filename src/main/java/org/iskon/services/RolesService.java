package org.iskon.services;

import java.util.List;

import org.iskon.models.Roles;
import org.iskon.models.RolesSearch;


public interface RolesService {

	Roles addRoles(Roles role);
	
	List<Roles> getRoles(RolesSearch role);
	
	Roles updateRoles(Roles role);

	void deleteRoles(Roles role);
	
}


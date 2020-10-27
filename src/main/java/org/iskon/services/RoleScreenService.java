package org.iskon.services;

import java.util.List;

import org.iskon.models.RoleScreen;
import org.iskon.models.RoleScreenSearch;


public interface RoleScreenService {

	RoleScreen addRoleScreen(RoleScreen rolescreen);
	
	List<RoleScreen> getRoleScreen(RoleScreenSearch rolescreen);
	
	RoleScreen updateRoleScreen(RoleScreen rolescreen);

	void deleteRoleScreen(RoleScreen rolescreen);
	
}


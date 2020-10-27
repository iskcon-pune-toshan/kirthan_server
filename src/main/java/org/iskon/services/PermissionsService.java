package org.iskon.services;

import java.util.List;

import org.iskon.models.Permissions;
import org.iskon.models.PermissionsSearch;


public interface PermissionsService {

	Permissions addPermissions(Permissions permissions);
	
	List<Permissions> getPermissions(PermissionsSearch permissions);
	
	Permissions updatePermissions(Permissions permissions);

	void deletePermissions(Permissions permissions);
	
}
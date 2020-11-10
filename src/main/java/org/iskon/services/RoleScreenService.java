package org.iskon.services;

import java.util.List;

import org.iskon.models.RoleScreen;
import org.iskon.models.RoleScreenSearch;

public interface RoleScreenService {

	List<RoleScreen> addRoleScreen(List<RoleScreen> listRolescreen);

	List<RoleScreen> getRoleScreen(RoleScreenSearch rolescreen);

	void deleteRoleScreen(List<RoleScreen> newRoleScreenMapping);

	List<RoleScreen> getRoleScreenWithDescription();

}

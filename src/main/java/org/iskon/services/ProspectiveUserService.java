package org.iskon.services;

import java.util.List;

import org.iskon.models.ProspectiveUser;
import org.iskon.models.ProspectiveUserSearch;
import org.iskon.models.EventSearch;


public interface ProspectiveUserService {

	ProspectiveUser addProspectiveUser(ProspectiveUser event);
	
	ProspectiveUser getProspectiveUserById(int id);

	List<ProspectiveUser> getProspectiveUser(ProspectiveUserSearch event);
	
	Boolean processProspectiveUser(ProspectiveUser event);

	ProspectiveUser updateProspectiveUser(ProspectiveUser event);

	void deleteProspectiveUser(ProspectiveUser event);
	
}


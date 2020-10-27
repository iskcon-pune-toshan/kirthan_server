package org.iskon.services;

import java.util.List;

import org.iskon.models.UserTemple;
import org.iskon.models.UserTempleSearch;


public interface UserTempleService {

	UserTemple addUserTemple(UserTemple usertemple);
	
	List<UserTemple> getUserTemple(UserTempleSearch usertemple);
	
	UserTemple updateUserTemple(UserTemple usertemple);

	void deleteUserTemple(UserTemple usertemple);
	
}


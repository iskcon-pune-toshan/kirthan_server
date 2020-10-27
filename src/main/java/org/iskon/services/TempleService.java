package org.iskon.services;

import java.util.List;

import org.iskon.models.Temple;
import org.iskon.models.TempleSearch;


public interface TempleService {

	Temple addTemple(Temple temple);
	
	List<Temple> getTemple(TempleSearch event);
	
	Temple updateTemple(Temple temple);

	void deleteTemple(Temple temple);
	
}


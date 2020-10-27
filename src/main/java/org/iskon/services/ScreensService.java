package org.iskon.services;

import java.util.List;

import org.iskon.models.Screens;
import org.iskon.models.ScreensSearch;


public interface ScreensService {

	Screens addScreens(Screens screen);
	
	List<Screens> getScreens(ScreensSearch screen);
	
	Screens updateScreens(Screens screen);

	void deleteScreens(Screens screen);
	
}


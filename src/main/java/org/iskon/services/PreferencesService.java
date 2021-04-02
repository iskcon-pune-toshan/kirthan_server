package org.iskon.services;

import java.util.List;

import org.iskon.models.Preferences;
import org.iskon.models.PreferencesSearch;
import org.iskon.models.Event;
import org.iskon.models.EventSearch;


public interface PreferencesService {

	Preferences addPreferences(Preferences eventpref);
	
	Preferences getEventPreferencesById(int id);

	List<Preferences> getEventPreferences(PreferencesSearch eventpref);

	Preferences updateEventPreferences(Preferences eventpref);

	List<Preferences> getPreferencesWithDescription(String username);

	//Integer getPreferenceDuration(PreferencesSearch eventSearch);

}


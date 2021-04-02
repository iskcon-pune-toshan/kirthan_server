package org.iskon.services;

import org.iskon.models.Preferences;
import org.iskon.models.PreferencesSearch;
import org.iskon.models.UserSearch;
import org.iskon.models.UserTemple;
import org.iskon.models.EventSearch;
import org.iskon.models.Event;
import org.iskon.repositories.PreferencesJpaRepository;
import org.iskon.repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class PreferencesServiceImpl implements PreferencesService {

	@Autowired
	private PreferencesJpaRepository preferencesJpaRepository;
	
	
	@Override
	public Preferences getEventPreferencesById(int id){
		return preferencesJpaRepository.findById(id);
	}
	
	@Override
	public Preferences addPreferences(Preferences event)
	{
		return preferencesJpaRepository.save(event);
	}

	@Override
	public Preferences updateEventPreferences(Preferences event)
	{
		return preferencesJpaRepository.save(event);
	}
	
//	@Override
//	public Integer getPreferenceDuration(PreferencesSearch eventSearch)
//	{
//		return eventSearch.getEventDuration();
//	}

	
	@Override
	public List<Preferences> getEventPreferences(PreferencesSearch eventSearch) 
	{
		return preferencesJpaRepository.findAll(new Specification<Preferences>(){

			@Override
			public Predicate toPredicate(Root<Preferences> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				
				if(eventSearch.getArea() != null)
					predicates.add(root.get("area").in(eventSearch.getArea()));
				
				if(eventSearch.getLocalAdmin() != null)
					predicates.add(root.get("localAdmin").in(eventSearch.getLocalAdmin()));
				
				if(eventSearch.getEventDuration() != null)
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("eventDuration"), eventSearch.getEventDuration()));

				if(eventSearch.getRequestAcceptance() != null)
					predicates.add(root.get("requestAcceptance").in(eventSearch.getRequestAcceptance()));
				
				if(eventSearch.getUserId() != null)
					predicates.add(criteriaBuilder.equal(root.get("userid"), eventSearch.getUserId()));

				

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	@Override
	public List<Preferences> getPreferencesWithDescription(String username) {
		System.out.println(preferencesJpaRepository.findAllWithDescription(username));
		return preferencesJpaRepository.findAllWithDescription(username);
	}

}

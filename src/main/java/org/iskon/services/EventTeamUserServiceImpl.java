package org.iskon.services;

import org.iskon.models.EventTeamSearch;
import org.iskon.models.EventTeamUser;
import org.iskon.models.EventTeamUserSearch;
import org.iskon.repositories.EventTeamUserJpaRepository;
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
public class EventTeamUserServiceImpl implements EventTeamUserService {

	@Autowired
	private EventTeamUserJpaRepository eventteamuserJpaRepository;

	@Override
	public List<EventTeamUser> addEventTeamUser(List<EventTeamUser> listEventTeamUser)
	{
		return eventteamuserJpaRepository.saveAll(listEventTeamUser);
	}

	@Override
	public void deleteEventTeamUser(List<EventTeamUser> listEventTeamUser)
	{
		eventteamuserJpaRepository.deleteAll(listEventTeamUser);
	}
	
	@Override
	public List<EventTeamUser> getEventTeamUsersByUserName(String username){
		return eventteamuserJpaRepository.findByUserName(username);
	}

	@Override
	public List<EventTeamUser> getEventTeamUsers(EventTeamUserSearch eventteamusersearch)
	{
		return eventteamuserJpaRepository.findAll(new Specification<EventTeamUser>(){

			@Override
			public Predicate toPredicate(Root<EventTeamUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				if(eventteamusersearch.getId()!=null)
					predicates.add(root.get("id").in(eventteamusersearch.getId()));
				
				if(eventteamusersearch.getTeamId()!=null)
					predicates.add(root.get("teamId").in(eventteamusersearch.getTeamId()));
				
				if(eventteamusersearch.getEventId()!=null)
					predicates.add(root.get("eventId").in(eventteamusersearch.getEventId()));
				
				if(eventteamusersearch.getUserId()!=null)
					predicates.add(root.get("userId").in(eventteamusersearch.getUserId()));
				if(eventteamusersearch.getUserName()!=null)
					predicates.add(root.get("userName").in(eventteamusersearch.getUserName()));
				System.out.println(predicates);
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
				
			}
		});
	}
	
	@Override
	public List<EventTeamUser> getEventTeamUsersWithDescription()
	{
		return eventteamuserJpaRepository.findAllWithDescription();
	}
}


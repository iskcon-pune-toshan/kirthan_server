package org.iskon.services;

import org.iskon.models.EventTeamSearch;
import org.iskon.models.EventUser;
import org.iskon.models.EventUserSearch;
import org.iskon.repositories.EventUserJpaRepository;
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
public class EventUserServiceImpl implements EventUserService {

	@Autowired
	private EventUserJpaRepository eventteamuserJpaRepository;

	@Override
	public List<EventUser> addEventTeamUser(List<EventUser> listEventTeamUser)
	{
		return eventteamuserJpaRepository.saveAll(listEventTeamUser);
	}

	@Override
	public void deleteEventTeamUser(List<EventUser> listEventTeamUser)
	{
		eventteamuserJpaRepository.deleteAll(listEventTeamUser);
	}
	
	@Override
	public List<EventUser> getEventTeamUsersByUserName(String username){
		return eventteamuserJpaRepository.findByUserName(username);
	}

	@Override
	public List<EventUser> getEventTeamUsers(EventUserSearch eventteamusersearch)
	{
		return eventteamuserJpaRepository.findAll(new Specification<EventUser>(){

			@Override
			public Predicate toPredicate(Root<EventUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
	public List<EventUser> getEventTeamUsersWithDescription()
	{
		return eventteamuserJpaRepository.findAllWithDescription();
	}
}


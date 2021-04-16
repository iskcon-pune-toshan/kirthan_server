package org.iskon.services;

import org.iskon.models.Team;
import org.iskon.models.EventTeam;
import org.iskon.repositories.EventTeamJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.iskon.models.EventTeamSearch;

@Component
public class EventTeamServiceImpl implements EventTeamService {

	@Autowired
	private EventTeamJpaRepository EventTeamJpaRepository;

	@Override
	public List<EventTeam> addEventTeam(List<EventTeam> listEventTeam) {
		//return teamuserJpaRepository.save(teamUser);
		return EventTeamJpaRepository.saveAll(listEventTeam);
	}

	@Override
	public void deleteEventTeam(EventTeam listEventTeam) {
		EventTeamJpaRepository.delete(listEventTeam);
		//teamuserJpaRepository.delete(teamUser);
	}

	@Override
	public List<EventTeam> getEventTeams(EventTeamSearch teamusersearch) {
		return EventTeamJpaRepository.findAll(new Specification<EventTeam>() {

			@Override
			public Predicate toPredicate(Root<EventTeam> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				
				if (teamusersearch.getId() != null)
					predicates.add(criteriaBuilder.equal(root.get("id"), teamusersearch.getId()));
				
				if(teamusersearch.getCreatedBy() != null)
					predicates.add(root.get("createdBy").in(teamusersearch.getCreatedBy()));

				if (teamusersearch.getEventId() != null)
					predicates.add(criteriaBuilder.equal(root.get("eventId"), teamusersearch.getEventId()));
				
				/*
				 * if(teamusersearch.getId()!=null)
				 * predicates.add(root.get("id").in(teamusersearch.getId()));
				 * 
				 * 
				 * if(teamusersearch.getCreatedBy() != null)
				 * predicates.add(root.get("createdBy").in(teamusersearch.getCreatedBy()));
				 * 
				 * if(teamusersearch.getTeamId()!=null)
				 * predicates.add(root.get("teamId").in(teamusersearch.getTeamId()));
				 */
				/*
				 * if(teamusersearch.getId()!=null)
				 * predicates.add(root.get("id").in(teamusersearch.getId()));
				 * 
				 * 
				 * if(teamusersearch.getCreatedBy() != null)
				 * predicates.add(root.get("createdBy").in(teamusersearch.getCreatedBy()));
				 * 
				 * if(teamusersearch.getTeamId()!=null)
				 * predicates.add(root.get("teamId").in(teamusersearch.getTeamId()));
				 */
				/*
				 * if(teamusersearch.getId()!=null)
				 * predicates.add(root.get("id").in(teamusersearch.getId()));
				 * 
				 * 
				 * if(teamusersearch.getCreatedBy() != null)
				 * predicates.add(root.get("createdBy").in(teamusersearch.getCreatedBy()));
				 * 
				 * if(teamusersearch.getTeamId()!=null)
				 * predicates.add(root.get("teamId").in(teamusersearch.getTeamId()));
				 */
				/*
				 * if(teamusersearch.getId()!=null)
				 * predicates.add(root.get("id").in(teamusersearch.getId()));
				 * 
				 * 
				 * if(teamusersearch.getCreatedBy() != null)
				 * predicates.add(root.get("createdBy").in(teamusersearch.getCreatedBy()));
				 * 
				 * if(teamusersearch.getTeamId()!=null)
				 * predicates.add(root.get("teamId").in(teamusersearch.getTeamId()));
				 */

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	@Override
	public List<EventTeam> getEventTeamsWithDescription() {
		return EventTeamJpaRepository.findAllWithDescription();
	}
}

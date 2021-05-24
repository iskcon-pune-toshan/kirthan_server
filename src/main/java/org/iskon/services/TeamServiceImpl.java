package org.iskon.services;

import org.iskon.models.Event;
import org.iskon.models.Team;
import org.iskon.models.TeamSearch;
import org.iskon.repositories.TeamJpaRepository;
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

@Component
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamJpaRepository teamJpaRepository;
	
	@Override
	public Team getTeamById(int id) {
		return teamJpaRepository.getTeamById(id);
	}
	
	@Override
	public Team addTeam(Team event)
	{
		return teamJpaRepository.save(event);
	}

	@Override
	public Team updateTeam(Team event)
	{
		return teamJpaRepository.save(event);
	}

	@Override
	public void deleteTeam(Team event)
	{
		teamJpaRepository.delete(event);
	}

	@Override
	public List<Team> getTeams(TeamSearch teamsearch)
	{
		return teamJpaRepository.findAll(new Specification<Team>(){

			@Override
			public Predicate toPredicate(Root<Team> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				if(teamsearch.getId()!=null)
					predicates.add(root.get("id").in(teamsearch.getId()));

				if(teamsearch.getCreatedBy() != null)
					predicates.add(root.get("createdBy").in(teamsearch.getCreatedBy()));

				if(teamsearch.getCreatedTime() != null)
					predicates.add(root.get("createdTime").in(teamsearch.getCreatedTime()));
				
				if (teamsearch.getApprovalStatus() != null)
					predicates.add(criteriaBuilder.equal(root.get("approvalStatus"),teamsearch.getApprovalStatus()));

				if (teamsearch.getTeamLeadId()!= null)
					predicates.add(criteriaBuilder.equal(root.get("teamLeadId"),teamsearch.getTeamLeadId()));
				
				if (teamsearch.getLocalAdminName()!= null)
					predicates.add(criteriaBuilder.equal(root.get("localAdminName"),teamsearch.getLocalAdminName()));
				
				if(teamsearch.getRequestAcceptance()!=null)
					predicates.add(root.get("requestAcceptance").in(teamsearch.getRequestAcceptance()));
				
				if(teamsearch.getDuration()!=null)
					predicates.add(root.get("duration").in(teamsearch.getDuration()));
				

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	@Override
	public Boolean processTeam(Team event)  {

		return teamJpaRepository.save(event) !=null;

	}
}

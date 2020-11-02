  package org.iskon.services;
  
  import org.iskon.models.Team;
import org.iskon.models.TeamUser; import
  org.iskon.repositories.TeamUserJpaRepository; import
  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import
  org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections; import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.iskon.models.TeamUserSearch;
  @Component public class TeamUserServiceImpl implements TeamUserService {
  
  @Autowired private TeamUserJpaRepository teamuserJpaRepository;
  
  @Override public TeamUser addTeamUser(TeamUser event) { return
  teamuserJpaRepository.save(event); }
  
  
  @Override public void deleteTeamUser(TeamUser event) {
  teamuserJpaRepository.delete(event); }
  
  @Override public List<TeamUser> getTeamUsers(TeamUserSearch teamusersearch) {
	  return teamuserJpaRepository.findAll(new Specification<TeamUser>(){

			@Override
			public Predicate toPredicate(Root<TeamUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				

				
				if(teamusersearch.getCreatedBy() != null)
					predicates.add(root.get("createdBy").in(teamusersearch.getCreatedBy()));


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
  public List<TeamUser> getTeamUsersWithDescription() {
	  return teamuserJpaRepository.findAllWithDescription();
	 }
 }
 

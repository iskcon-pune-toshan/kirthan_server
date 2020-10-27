package org.iskon.services;

import org.iskon.models.Roles;
import org.iskon.models.RolesSearch;
import org.iskon.repositories.RolesJpaRepository;
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
public class RolesServiceImpl implements RolesService {

	@Autowired
	private RolesJpaRepository rolesJpaRepository;

	@Override
	public Roles addRoles(Roles role)
	{
		return rolesJpaRepository.save(role);
	}

	@Override
	public Roles updateRoles(Roles role)
	{
		return rolesJpaRepository.save(role);
	}

	@Override
	public void deleteRoles(Roles role)
	{
		rolesJpaRepository.delete(role);
	}

	@Override
	public List<Roles> getRoles(RolesSearch roleSearch)
	{
		return rolesJpaRepository.findAll(new Specification<Roles>(){

			@Override
			public Predicate toPredicate(Root<Roles> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				if(roleSearch.getId() != null)
					predicates.add(root.get("id").in(roleSearch.getId()));

				if (roleSearch.getRoleName() != null)
					predicates.add(root.get("roleName").in(roleSearch.getRoleName()));


				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	
}

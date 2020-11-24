package org.iskon.services;

import org.iskon.models.RoleScreen;
import org.iskon.models.RoleScreenSearch;
import org.iskon.models.UserTemple;
import org.iskon.repositories.RoleScreenJpaRepository;
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
public class RoleScreenServiceImpl implements RoleScreenService {

	@Autowired
	private RoleScreenJpaRepository rolescreenJpaRepository;

	@Override
	public List<RoleScreen> addRoleScreen(List<RoleScreen> listRoleScreen)
	{
		System.out.println(listRoleScreen.toString());
		return rolescreenJpaRepository.saveAll(listRoleScreen);
	}


	@Override
	public List<RoleScreen> getRoleScreen(RoleScreenSearch roleSearch)
	{
		return rolescreenJpaRepository.findAll(new Specification<RoleScreen>(){

			@Override
			public Predicate toPredicate(Root<RoleScreen> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				if(roleSearch.getId() != null)
					predicates.add(root.get("id").in(roleSearch.getId()));

				/*
				 * if (roleSearch.getRoleId() != null)
				 * predicates.add(root.get("roleId").in(roleSearch.getRoleId()));
				 * 
				 * if (roleSearch.getScreenId() != null)
				 * predicates.add(root.get("screenId").in(roleSearch.getScreenId()));
				 */
			

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}
	
	@Override
	public void deleteRoleScreen(List<RoleScreen> newRoleScreenMapping)
	{
		rolescreenJpaRepository.deleteAll(newRoleScreenMapping);
	}
	
	@Override
	public List<RoleScreen> getRoleScreenWithDescription() {
		return rolescreenJpaRepository.findAllWithDescription();
	}

	
}

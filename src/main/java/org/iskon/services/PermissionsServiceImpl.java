package org.iskon.services;

import org.iskon.models.PermissionsSearch;
import org.iskon.models.Permissions;
import org.iskon.repositories.PermissionsJpaRepository;
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
public class PermissionsServiceImpl implements PermissionsService {

	@Autowired
	private PermissionsJpaRepository permissionsJpaRepository;

	@Override
	public Permissions addPermissions(Permissions Permissions)
	{
		return permissionsJpaRepository.save(Permissions);
	}

	@Override
	public Permissions updatePermissions(Permissions Permissions)
	{
		return permissionsJpaRepository.save(Permissions);
	}

	@Override
	public void deletePermissions(Permissions Permissions)
	{
		permissionsJpaRepository.delete(Permissions);
	}

	@Override
	public List<Permissions> getPermissions(PermissionsSearch PermissionsSearch)
	{
		return permissionsJpaRepository.findAll(new Specification<Permissions>(){

			@Override
			public Predicate toPredicate(Root<Permissions> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				if(PermissionsSearch.getId() != null)
					predicates.add(root.get("id").in(PermissionsSearch.getId()));

				if(PermissionsSearch.getName() != null)
					predicates.add(root.get("name").in(PermissionsSearch.getName()));
				
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	
}

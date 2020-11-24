package org.iskon.services;

//import org.iskon.models.TeamUser;
import org.iskon.models.UserTemple;
import org.iskon.models.UserTempleSearch;
import org.iskon.repositories.UserTempleJpaRepository;
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
public class UserTempleServiceImpl implements UserTempleService {

	@Autowired
	private UserTempleJpaRepository usertempleJpaRepository;

	@Override
	public List<UserTemple> addUserTemple(List<UserTemple> listUsertemple) {
		return usertempleJpaRepository.saveAll(listUsertemple);
	}


	@Override
	public List<UserTemple> getUserTemple(UserTempleSearch eventSearch) {
		return usertempleJpaRepository.findAll(new Specification<UserTemple>() {

			@Override
			public Predicate toPredicate(Root<UserTemple> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				if (eventSearch.getId() != null)
					predicates.add(root.get("id").in(eventSearch.getId()));

				/*
				 * if (eventSearch.getRoleId() != null)
				 * predicates.add(root.get("roleId").in(eventSearch.getRoleId()));
				 * 
				 * if (eventSearch.getTempleId() != null)
				 * predicates.add(root.get("templeId").in(eventSearch.getTempleId()));
				 * 
				 * if (eventSearch.getUserId() != null)
				 * predicates.add(root.get("userId").in(eventSearch.getUserId()));
				 */

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	@Override
	public void deleteUserTemple(List<UserTemple> listUsertemple) {
		usertempleJpaRepository.deleteAll(listUsertemple);
	}
	
	@Override
	public List<UserTemple> getUserTempleWithDescription() {
		System.out.println(usertempleJpaRepository.findAllWithDescription());
		return usertempleJpaRepository.findAllWithDescription();
	}

}

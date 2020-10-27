package org.iskon.services;

import org.iskon.models.Temple;
import org.iskon.models.TempleSearch;
import org.iskon.repositories.TempleJpaRepository;
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
public class TempleServiceImpl implements TempleService {

	@Autowired
	private TempleJpaRepository templeJpaRepository;

	@Override
	public Temple addTemple(Temple temple)
	{
		return templeJpaRepository.save(temple);
	}

	@Override
	public Temple updateTemple(Temple temple)
	{
		return templeJpaRepository.save(temple);
	}

	@Override
	public void deleteTemple(Temple temple)
	{
		templeJpaRepository.delete(temple);
	}

	@Override
	public List<Temple> getTemple(TempleSearch eventSearch)
	{
		return templeJpaRepository.findAll(new Specification<Temple>(){

			@Override
			public Predicate toPredicate(Root<Temple> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				if(eventSearch.getId() != null)
					predicates.add(root.get("id").in(eventSearch.getId()));

				if (eventSearch.getTempleName() != null)
					predicates.add(root.get("templeName").in(eventSearch.getTempleName()));

				if(eventSearch.getCity() != null)
					predicates.add(root.get("city").in(eventSearch.getCity()));

				if (eventSearch.getCity() != null)
					predicates.add(root.get("city").in(eventSearch.getCity()));


				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	
}

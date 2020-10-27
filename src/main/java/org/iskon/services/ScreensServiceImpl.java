package org.iskon.services;

import org.iskon.models.Screens;
import org.iskon.models.ScreensSearch;
import org.iskon.repositories.ScreensJpaRepository;
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
public class ScreensServiceImpl implements ScreensService {

	@Autowired
	private ScreensJpaRepository screensJpaRepository;

	@Override
	public Screens addScreens(Screens screen)
	{
		return screensJpaRepository.save(screen);
	}

	@Override
	public Screens updateScreens(Screens screen)
	{
		return screensJpaRepository.save(screen);
	}

	@Override
	public void deleteScreens(Screens screen)
	{
		screensJpaRepository.delete(screen);
	}

	@Override
	public List<Screens> getScreens(ScreensSearch screenSearch)
	{
		return screensJpaRepository.findAll(new Specification<Screens>(){

			@Override
			public Predicate toPredicate(Root<Screens> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				if(screenSearch.getId() != null)
					predicates.add(root.get("id").in(screenSearch.getId()));

				if (screenSearch.getScreenName() != null)
					predicates.add(root.get("screenName").in(screenSearch.getScreenName()));


				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	
}

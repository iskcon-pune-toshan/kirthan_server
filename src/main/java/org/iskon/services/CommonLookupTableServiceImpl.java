package org.iskon.services;

import org.iskon.models.CommonLookupTable;
import org.iskon.models.CommonLookupTableSearch;
import org.iskon.repositories.CommonLookupTableJpaRepository;
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
public class CommonLookupTableServiceImpl implements CommonLookupTableService {

	@Autowired
	private CommonLookupTableJpaRepository cltJpaRepository;

	@Override
	public CommonLookupTable addCommonLookupTable(CommonLookupTable role)
	{
		return cltJpaRepository.save(role);
	}

	@Override
	public CommonLookupTable updateCommonLookupTable(CommonLookupTable role)
	{
		return cltJpaRepository.save(role);
	}

	@Override
	public void deleteCommonLookupTable(CommonLookupTable role)
	{
		cltJpaRepository.delete(role);
	}

	@Override
	public List<CommonLookupTable> getCommonLookupTable(CommonLookupTableSearch roleSearch)
	{
		return cltJpaRepository.findAll(new Specification<CommonLookupTable>(){

			@Override
			public Predicate toPredicate(Root<CommonLookupTable> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				if(roleSearch.getId() != null)
					predicates.add(root.get("id").in(roleSearch.getId()));

				if (roleSearch.getDescription() != null)
					predicates.add(root.get("description").in(roleSearch.getDescription()));

				if (roleSearch.getLookupType() != null)
					predicates.add(root.get("lookupType").in(roleSearch.getLookupType()));


				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	
}

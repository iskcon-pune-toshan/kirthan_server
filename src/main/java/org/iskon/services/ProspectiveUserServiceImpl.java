package org.iskon.services;

import org.iskon.models.ProspectiveUser;

import java.time.ZoneId;
import java.util.Date;
import java.util.Date;
import org.iskon.models.ProspectiveUserSearch;
import org.iskon.repositories.ProspectiveUserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProspectiveUserServiceImpl implements ProspectiveUserService {

	@Autowired
	private ProspectiveUserJpaRepository prospectiveUserJpaRepository;

	@Override
	public ProspectiveUser getProspectiveUserById(int id){
		return prospectiveUserJpaRepository.findById(id);
	}
	
	@Override
	public ProspectiveUser addProspectiveUser(ProspectiveUser event)
	{
		return prospectiveUserJpaRepository.save(event);
	}

	@Override
	public ProspectiveUser updateProspectiveUser(ProspectiveUser event)
	{
		return prospectiveUserJpaRepository.save(event);
	}

	@Override
	public void deleteProspectiveUser(ProspectiveUser event)
	{
		prospectiveUserJpaRepository.delete(event);
	}

	@Override
	public List<ProspectiveUser> getProspectiveUser(ProspectiveUserSearch eventSearch)
	{
		return prospectiveUserJpaRepository.findAll(new Specification<ProspectiveUser>(){

			@Override
			public Predicate toPredicate(Root<ProspectiveUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				
				if (eventSearch.getId() != null)
					predicates.add(criteriaBuilder.equal(root.get("id"), eventSearch.getId()));

				if (eventSearch.getInviteType() != null)
					predicates.add(root.get("inviteType").in(eventSearch.getInviteType()));

				if (eventSearch.getUserEmail() != null)
					predicates.add(root.get("userEmail").in(eventSearch.getUserEmail()));
				
				if (eventSearch.getLocalAdminEmail() != null)
					predicates.add(root.get("localAdminEmail").in(eventSearch.getLocalAdminEmail()));
				
				if (eventSearch.getInviteCode() != null)
					predicates.add(root.get("inviteCode").in(eventSearch.getInviteCode()));
				
				if(eventSearch.getIsProcessed() != null)
					predicates.add(criteriaBuilder.equal(root.get("isProcessed") ,eventSearch.getIsProcessed()));

				
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	@Override
	public Boolean processProspectiveUser(ProspectiveUser event)  {

		return prospectiveUserJpaRepository.save(event) !=null;

	}
	
}
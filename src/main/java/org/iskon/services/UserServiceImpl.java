package org.iskon.services;

import org.iskon.models.Team;
import org.iskon.models.User;
import org.iskon.repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.iskon.exceptions.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.iskon.models.UserSearch;
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@Override
	public User addUser(User event)
	{
		return userJpaRepository.save(event);
	}
	
	@Override
	public User getUserById(int id) {
		return userJpaRepository.findById(id);
	}
	
	@Override
	public User updateUser(User event)
	{
		return userJpaRepository.save(event);
	}

	@Override
	public void deleteUser(User event)
	{
		userJpaRepository.delete(event);
	}

	@Override
	public List<User> getUsers(UserSearch usersearch)
	{
		return userJpaRepository.findAll(new Specification<User>(){
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				if(usersearch.getId()!=null)
					predicates.add(root.get("id").in(usersearch.getId()));

				if (usersearch.getIsProcessed() != null)
					predicates.add(criteriaBuilder.equal(root.get("isProcessed"),usersearch.getIsProcessed()));

				if(usersearch.getFirstName() != null)
					predicates.add(root.get("firstName").in(usersearch.getFirstName()));

				if(usersearch.getLastName() != null)
					predicates.add(root.get("lastName").in(usersearch.getLastName()));

				

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	@Override
	public Boolean processUser(User event)  {

		return userJpaRepository.save(event) !=null;

	}

	@Override
	public Optional<User> getUserByEmailId(String username) {
		
		return userJpaRepository.findByEmail(username);
	}

	@Transactional
	@Override
	public void updateDeviceToken(String username, String deviceToken) {
		System.out.println(username);
		userJpaRepository.updateDeviceTokenByUserName(username,deviceToken);
	}



}

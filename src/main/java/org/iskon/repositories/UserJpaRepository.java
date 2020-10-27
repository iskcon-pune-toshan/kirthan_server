package org.iskon.repositories;


import org.iskon.models.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {
    List<User> findAll(Specification<User> eventSpecification);
    User findById(int id);

}

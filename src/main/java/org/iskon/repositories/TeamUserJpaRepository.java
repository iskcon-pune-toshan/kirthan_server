package org.iskon.repositories;


import org.iskon.models.TeamUser;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamUserJpaRepository extends JpaRepository<TeamUser, Integer> {
    List<TeamUser> findAll(Specification<TeamUser> eventSpecification);
}

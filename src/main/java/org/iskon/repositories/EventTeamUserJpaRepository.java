package org.iskon.repositories;


import org.iskon.models.EventTeamUser;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventTeamUserJpaRepository extends JpaRepository<EventTeamUser, Integer> {
    List<EventTeamUser> findAll(Specification<EventTeamUser> eventSpecification);
}

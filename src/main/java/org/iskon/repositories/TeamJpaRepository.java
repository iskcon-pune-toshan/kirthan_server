package org.iskon.repositories;


import org.iskon.models.Team;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamJpaRepository extends JpaRepository<Team, Integer> {
    List<Team> findAll(Specification<Team> eventSpecification);
}

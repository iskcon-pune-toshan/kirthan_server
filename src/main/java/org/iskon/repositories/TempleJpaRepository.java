package org.iskon.repositories;


import org.iskon.models.Temple;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempleJpaRepository extends JpaRepository<Temple, Integer> {
    List<Temple> findAll(Specification<Temple> eventSpecification);
}

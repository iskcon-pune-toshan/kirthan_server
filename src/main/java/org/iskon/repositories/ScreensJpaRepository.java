package org.iskon.repositories;


import org.iskon.models.Screens;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreensJpaRepository extends JpaRepository<Screens, Integer> {
    List<Screens> findAll(Specification<Screens> eventSpecification);
}

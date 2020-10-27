package org.iskon.repositories;


import org.iskon.models.RoleScreen;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleScreenJpaRepository extends JpaRepository<RoleScreen, Integer> {
    List<RoleScreen> findAll(Specification<RoleScreen> eventSpecification);
}

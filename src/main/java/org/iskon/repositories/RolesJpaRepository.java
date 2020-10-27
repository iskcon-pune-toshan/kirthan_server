package org.iskon.repositories;


import org.iskon.models.Roles;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesJpaRepository extends JpaRepository<Roles, Integer> {
    List<Roles> findAll(Specification<Roles> eventSpecification);
}

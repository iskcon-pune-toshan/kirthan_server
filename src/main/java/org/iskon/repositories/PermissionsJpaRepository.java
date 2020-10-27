package org.iskon.repositories;


import org.iskon.models.Permissions;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionsJpaRepository extends JpaRepository<Permissions, Integer> {
    List<Permissions> findAll(Specification<Permissions> eventSpecification);
}

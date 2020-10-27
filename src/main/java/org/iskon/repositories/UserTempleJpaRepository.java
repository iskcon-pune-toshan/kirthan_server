package org.iskon.repositories;


import org.iskon.models.UserTemple;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTempleJpaRepository extends JpaRepository<UserTemple, Integer> {
    List<UserTemple> findAll(Specification<UserTemple> eventSpecification);
}

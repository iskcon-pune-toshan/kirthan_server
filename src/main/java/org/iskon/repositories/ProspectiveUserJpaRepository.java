package org.iskon.repositories;


import org.iskon.models.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProspectiveUserJpaRepository extends JpaRepository<ProspectiveUser, Integer> {
    List<ProspectiveUser> findAll(Specification<ProspectiveUser> eventSpecification);
    ProspectiveUser findById(int id);

}


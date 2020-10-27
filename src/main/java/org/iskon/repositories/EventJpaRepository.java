package org.iskon.repositories;


import org.iskon.models.Event;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventJpaRepository extends JpaRepository<Event, Integer> {
    List<Event> findAll(Specification<Event> eventSpecification);
    Event findById(int id);

}


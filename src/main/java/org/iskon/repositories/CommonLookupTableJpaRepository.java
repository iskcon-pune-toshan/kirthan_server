package org.iskon.repositories;


import org.iskon.models.CommonLookupTable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonLookupTableJpaRepository extends JpaRepository<CommonLookupTable, Integer> {
    List<CommonLookupTable> findAll(Specification<CommonLookupTable> eventSpecification);
}

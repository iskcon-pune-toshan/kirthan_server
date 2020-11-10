package org.iskon.repositories;


import org.iskon.models.RoleScreen;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleScreenJpaRepository extends JpaRepository<RoleScreen, Integer> {
    List<RoleScreen> findAll(Specification<RoleScreen> eventSpecification);
    
    @Query("SELECT new org.iskon.models.RoleScreen(RS.id, RS.roleId, RS.screenId, RS.create, RS.update, RS.delete, RS.view, RS.process, R.roleName, S.screenName) "
			+ " FROM RoleScreen RS, Roles R, Screens S " + " where RS.roleId = R.id " + " and RS.screenId = S.id ")
	List<RoleScreen> findAllWithDescription();
}

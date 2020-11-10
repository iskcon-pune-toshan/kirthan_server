package org.iskon.repositories;

import java.util.List;

import org.iskon.models.UserTemple;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTempleJpaRepository extends JpaRepository<UserTemple, Integer> {
	List<UserTemple> findAll(Specification<UserTemple> eventSpecification);

	@Query(" SELECT new org.iskon.models.UserTemple(UT.id, UT.templeId, UT.roleId, UT.userId, T.templeName, U.userName) "
			+ " FROM UserTemple UT, Temple T, User U " + " where UT.templeId = T.id " + " and UT.userId = U.id ")
	List<UserTemple> findAllTwo();
}

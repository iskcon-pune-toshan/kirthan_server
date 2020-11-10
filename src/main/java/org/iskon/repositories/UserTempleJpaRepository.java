package org.iskon.repositories;

import java.util.List;

import org.iskon.models.UserTemple;
<<<<<<< HEAD
=======
import org.springframework.data.jpa.repository.Query;
>>>>>>> branch 'jpaimplementaion' of https://github.com/iskcon-pune-toshan/kirthan_server.git
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface UserTempleJpaRepository extends JpaRepository<UserTemple, Integer> {
	List<UserTemple> findAll(Specification<UserTemple> eventSpecification);

	@Query("SELECT new org.iskon.models.UserTemple(UT.id, UT.templeId, UT.roleId, UT.userId, T.templeName, U.userName) "
			+ " FROM UserTemple UT, Temple T, User U " + " where UT.templeId = T.id " + " and UT.userId = U.id ")
	List<UserTemple> findAllTwo();
	
}

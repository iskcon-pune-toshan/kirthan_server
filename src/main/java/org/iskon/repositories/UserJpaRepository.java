package org.iskon.repositories;


import org.iskon.models.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {
    List<User> findAll(Specification<User> eventSpecification);
    User findById(int id);
    Optional<User> findByEmail(String email);
	
    @Modifying
    @Query("Update User u set u.deviceToken = :deviceToken where u.email = :username ")
    void updateDeviceTokenByUserName(String username,String deviceToken	);
}

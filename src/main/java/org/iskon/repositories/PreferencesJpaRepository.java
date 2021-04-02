package org.iskon.repositories;


import org.iskon.models.Event;
import org.iskon.models.Preferences;
import org.iskon.models.UserTemple;
import org.iskon.models.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreferencesJpaRepository extends JpaRepository<Preferences, Integer> {
    List<Preferences> findAll(Specification<Preferences> eventSpecification);
    Preferences findById(int id);
    
    @Query(" SELECT new org.iskon.models.Preferences(P.id,P.userid, P.area, P.localAdmin, P.eventDuration, P.requestAcceptance, P.interestedEventId) "
			+ " FROM Preferences P" + " where  P.userid = :username ")
	List<Preferences> findAllWithDescription(String username);

}


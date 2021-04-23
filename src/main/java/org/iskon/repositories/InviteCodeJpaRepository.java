package org.iskon.repositories;

import org.iskon.models.InviteCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteCodeJpaRepository extends JpaRepository<InviteCode, String> {
}

package com.goalproject.backend.persistence.jpa;

import com.goalproject.backend.persistence.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserJpaRepository extends JpaRepository<AppUserEntity, Long> {
}

package com.goalproject.backend.persistence.jpa;

import com.goalproject.backend.persistence.entity.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalJpaRepository extends JpaRepository<GoalEntity, Long> {
}

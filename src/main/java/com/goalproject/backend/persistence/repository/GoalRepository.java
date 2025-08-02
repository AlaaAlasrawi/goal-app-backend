package com.goalproject.backend.persistence.repository;

import com.goalproject.backend.domain.model.Goal;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository {
    Long save(Goal goal);
}

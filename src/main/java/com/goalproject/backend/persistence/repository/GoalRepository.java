package com.goalproject.backend.persistence.repository;

import com.goalproject.backend.domain.model.Goal;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository {
    Goal save(Goal goal);

    Goal getGoalById(Long id);

    Page<Goal> getAllGoals(int page, int size, String sortBy, String sortDirection, Long userId);
}

package com.goalproject.backend.domain.service;

import com.goalproject.backend.domain.model.AppUser;
import com.goalproject.backend.domain.model.Goal;
import com.goalproject.backend.domain.providers.IdentityProvider;
import com.goalproject.backend.persistence.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final IdentityProvider identityProvider;

    public Long createGoalService(Goal goal) {
        Long userId = identityProvider.currentId();

        goal.setUserId(userId);

        if (goal.getCreatedAt() == null) {
            goal.setCreatedAt(LocalDateTime.now());
        }

        goal.setUpdatedAt(LocalDateTime.now());
        Goal savedGoal = goalRepository.save(goal);
        return savedGoal.getId();
    }

    public Goal getGoalById(Long id) {
        return goalRepository.getGoalById(id);
    }

    public Page<Goal> getAllGoals(int page, int size, String sortBy, String sortDirection) {
        Long userId = identityProvider.currentId();

        return goalRepository.getAllGoals(page, size, sortBy, sortDirection, userId);
    }

    public Boolean deleteGoalById(Long id) {
        return goalRepository.deleteGoalById(id);
    }

    public Goal updateGoalById(Long id, Goal newGoal) {
        Goal oldGoal = goalRepository.getGoalById(id);

        if (oldGoal == null) {
            throw new GoalNotFoundException(id);
        }

        Long userId = identityProvider.currentId();
        if (!oldGoal.getUserId().equals(userId)) {
            throw new UnauthorizedAccessException();
        }
        1
        oldGoal.setTitle(newGoal.getTitle());
        oldGoal.setDescription(newGoal.getDescription());
        oldGoal.setCategory(newGoal.getCategory());
        oldGoal.setDueDate(newGoal.getDueDate());
        oldGoal.setUpdatedAt(LocalDateTime.now());

        return goalRepository.save(oldGoal);
    }
}

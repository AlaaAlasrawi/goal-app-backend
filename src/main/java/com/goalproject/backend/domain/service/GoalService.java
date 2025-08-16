package com.goalproject.backend.domain.service;

import com.goalproject.backend.application.exception.ResourceNotFoundException;
import com.goalproject.backend.domain.model.AppUser;
import com.goalproject.backend.domain.model.Goal;
import com.goalproject.backend.domain.providers.IdentityProvider;
import com.goalproject.backend.persistence.repository.AppUserRepository;
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
    private final AppUserRepository appUserRepository;

    public Long createGoalService(Goal goal) {
        Long userId = identityProvider.currentId();

        goal.setUserId(userId);

        if (goal.getCreatedAt() == null) {
            goal.setCreatedAt(LocalDateTime.now());
        }

        goal.setUpdatedAt(LocalDateTime.now());
        goal.setIsCompleted(false);
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
            throw new ResourceNotFoundException("Goal with ID " + id + " was not found.");

        }

        Long userId = identityProvider.currentId();
        if (!oldGoal.getUserId().equals(userId)) {
            throw new ResourceNotFoundException("You are not authorized to update this goal.");
        }

        oldGoal.setTitle(newGoal.getTitle());
        oldGoal.setDescription(newGoal.getDescription());
        oldGoal.setCategory(newGoal.getCategory());
        oldGoal.setDueDate(newGoal.getDueDate());
        oldGoal.setUpdatedAt(LocalDateTime.now());

        return goalRepository.save(oldGoal);
    }

    public Boolean deleteAllGoals() {
        Long userId = identityProvider.currentId();
        Long deletedCount = goalRepository.deleteAllByUserId(userId);
        return deletedCount > 0;
    }

    public Boolean toggleGoal(Long id) {
        Goal goal = goalRepository.getGoalById(id);
        AppUser user = identityProvider.currentIdentity();

        if (goal == null) {
            throw new ResourceNotFoundException("Goal with ID " + id + " was not found.");
        } else if (goal.getIsCompleted()) {
            user.setNoCompletedGoals(Math.max(0, user.getNoCompletedGoals() - 1));
        } else {
            user.setNoCompletedGoals(user.getNoCompletedGoals() + 1);
        }

        goal.setIsCompleted(!Boolean.TRUE.equals(goal.getIsCompleted()));
        goal.setUpdatedAt(LocalDateTime.now());
        goalRepository.save(goal);
        appUserRepository.save(user);

        return goal.getIsCompleted();
    }

    public Long getCompletedGoalsCount() {
        Long userId = identityProvider.currentId();
        return goalRepository.getCompletedGoalsCount(userId);
    }
}

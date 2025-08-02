package com.goalproject.backend.domain.service;

import com.goalproject.backend.domain.model.AppUser;
import com.goalproject.backend.domain.model.Goal;
import com.goalproject.backend.domain.providers.IdentityProvider;
import com.goalproject.backend.persistence.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final IdentityProvider identityProvider;

    public Long createGoalService(Goal goal) {
        Long userId =  identityProvider.currentId();

        goal.setUserId(userId);

        if( goal.getCreatedAt() == null) {
            goal.setCreatedAt(LocalDateTime.now());
        }

        goal.setUpdatedAt(LocalDateTime.now());
        return goalRepository.save(goal);
    }
}

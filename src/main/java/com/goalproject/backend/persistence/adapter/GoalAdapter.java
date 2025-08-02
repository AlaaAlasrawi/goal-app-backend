package com.goalproject.backend.persistence.adapter;

import com.goalproject.backend.domain.mapper.GoalMapper;
import com.goalproject.backend.domain.model.Goal;
import com.goalproject.backend.persistence.jpa.GoalJpaRepository;
import com.goalproject.backend.persistence.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GoalAdapter implements GoalRepository {

    private final GoalJpaRepository goalJpaRepository;
    private final GoalMapper goalMapper;

    @Override
    public Long save(Goal goal) {
        return goalMapper.entityToModel(goalJpaRepository.save(goalMapper.modelToEntity(goal)));
    }
}

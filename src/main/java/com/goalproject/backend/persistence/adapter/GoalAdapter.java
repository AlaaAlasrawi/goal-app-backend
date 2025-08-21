package com.goalproject.backend.persistence.adapter;

import com.goalproject.backend.domain.mapper.GoalMapper;
import com.goalproject.backend.domain.model.Goal;
import com.goalproject.backend.persistence.entity.GoalEntity;
import com.goalproject.backend.persistence.jpa.GoalJpaRepository;
import com.goalproject.backend.persistence.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GoalAdapter implements GoalRepository {

    private final GoalJpaRepository goalJpaRepository;
    private final GoalMapper goalMapper;

    @Override
    public Goal save(Goal goal) {
        return goalMapper.entityToModel(goalJpaRepository.save(goalMapper.modelToEntity(goal)));
    }

    @Override
    public Goal getGoalById(Long id) {
        return goalMapper.entityToModel(goalJpaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Goal with id " + id + " not found!")
        ));
    }

    @Override
    public Page<Goal> getAllGoals(int page, int size, String sortBy, String sortDirection, Long userId) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<GoalEntity> entities = goalJpaRepository.findAllByUserId(userId,pageable);

        List<Goal> goals = entities
                .getContent()
                .stream()
                .map(goalMapper::entityToModel)
                .toList();

        return new PageImpl<>(goals, pageable, entities.getTotalElements());
    }

    @Override
    public Boolean deleteGoalById(Long id) {
        if (getGoalById(id) == null) {
            return false;
        }

        goalJpaRepository.deleteById(id);
        return true;
    }

    @Override
    public Long deleteAllByUserId(Long userId) {
        return goalJpaRepository.deleteAllByUserId(userId);
    }

    @Override
    public Long getCompletedGoalsCount(Long userId) {
        return goalJpaRepository.getCompletedGoalsCount(userId);
    }
}

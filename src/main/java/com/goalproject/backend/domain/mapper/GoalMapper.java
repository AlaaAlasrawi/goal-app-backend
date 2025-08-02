package com.goalproject.backend.domain.mapper;

import com.goalproject.backend.application.dtos.goal.CreateGoalRequest;
import com.goalproject.backend.domain.model.Goal;
import com.goalproject.backend.persistence.entity.GoalEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GoalMapper {

   Goal requestToModel(CreateGoalRequest request) ;

    GoalEntity modelToEntity(Goal goal);

    Goal entityToModel(GoalEntity save);
}

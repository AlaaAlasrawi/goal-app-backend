package com.goalproject.backend.application.controllers;

import com.goalproject.backend.application.dtos.goal.CreateGoalRequest;
import com.goalproject.backend.domain.mapper.GoalMapper;
import com.goalproject.backend.domain.model.Goal;
import com.goalproject.backend.domain.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/goal")
public class GoalController {

    private final GoalMapper goalMapper;
    private final GoalService goalService;

    @PostMapping
    public ResponseEntity<Long>createGoal(@RequestBody CreateGoalRequest createGoalRequest){
        Goal goal = goalMapper.requestToModel(createGoalRequest);
        return ResponseEntity.ok(goalService.createGoalService(goal));
    }

}

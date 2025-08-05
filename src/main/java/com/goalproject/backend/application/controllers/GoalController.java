package com.goalproject.backend.application.controllers;

import com.goalproject.backend.application.dtos.goal.CreateGoalRequest;
import com.goalproject.backend.domain.mapper.GoalMapper;
import com.goalproject.backend.domain.model.Goal;
import com.goalproject.backend.domain.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/goal")
public class GoalController {

    private final GoalMapper goalMapper;
    private final GoalService goalService;

    @PostMapping
    public ResponseEntity<Long> createGoal(@RequestBody CreateGoalRequest request) {
        Goal goal = goalMapper.requestToModel(request);
        return ResponseEntity.ok(goalService.createGoalService(goal));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Goal> getGoalById(@PathVariable Long id) {
        return ResponseEntity.ok(goalService.getGoalById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Goal>> getAllGoals(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size,
            @RequestParam(defaultValue = "createdAt", name = "sortBy") String sortBy,
            @RequestParam(defaultValue = "desc", name = "sortDirection") String sortDirection) {
        return ResponseEntity.ok(goalService.getAllGoals(page, size, sortBy, sortDirection));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Goal> updateGoalById(@PathVariable Long id, @RequestBody CreateGoalRequest request) {
        Goal goal = goalMapper.requestToModel(request);
        return ResponseEntity.ok(goalService.updateGoalById(id, goal));
    }

    @PutMapping("/{id}/completed")
    public ResponseEntity<Boolean> toggleGoal(@PathVariable Long id) {
        return ResponseEntity.ok(goalService.toggleGoal(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteGoalById(@PathVariable Long id) {
        return ResponseEntity.ok(goalService.deleteGoalById(id));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteAllGoals() {
        return ResponseEntity.ok(goalService.deleteAllGoals());
    }
}

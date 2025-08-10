package com.goalproject.backend.application.controllers;

import com.goalproject.backend.domain.model.AppUser;
import com.goalproject.backend.domain.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping("/completed-goals-count")
    public ResponseEntity<Long> getCompletedGoalsCountForCurrentUser() {
        return ResponseEntity.ok(appUserService.getCompletedGoalsCount());
    }

    @GetMapping("/profile")
    public ResponseEntity<AppUser> getCurrentUserProfile() {
        return ResponseEntity.ok(appUserService.getCurrentUserProfile());
    }

}

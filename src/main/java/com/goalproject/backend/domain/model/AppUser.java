package com.goalproject.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    private Long id;

    private String username;

    private String password;

    private String fullName;

    private String email;

    private Long noCompletedGoals;
}

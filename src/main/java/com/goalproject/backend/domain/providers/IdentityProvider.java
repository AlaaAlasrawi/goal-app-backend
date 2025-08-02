package com.goalproject.backend.domain.providers;

import com.goalproject.backend.domain.model.AppUser;
import com.goalproject.backend.domain.service.AppUserService;
import com.goalproject.backend.persistence.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IdentityProvider {

    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;

    public AppUser currentIdentity() {
        AppUser user = AppUser.builder()
                .id(1L)
                .username("user1")
                .password("123")
                .email("user@email.com")
                .fullName("user")
                .noCompletedGoals(5L)
                .build();
        return null;
    }

    public Long currentId() {
        return 1L;
    }

}

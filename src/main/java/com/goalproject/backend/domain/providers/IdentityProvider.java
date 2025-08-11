package com.goalproject.backend.domain.providers;

import com.goalproject.backend.domain.model.AppUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IdentityProvider {

    public AppUser currentIdentity() {
      AppUser user = AppUser.builder()
                .id(1L)
                .username("user1")
                .password("123")
                .email("user@email.com")
                .fullName("user")
                .noCompletedGoals(3L)
                .build();
        return user;
    }

    public Long currentId() {
        return 1L;
    }

}

package com.goalproject.backend.domain.service;

import com.goalproject.backend.domain.model.AppUser;
import com.goalproject.backend.domain.providers.IdentityProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {
    private final IdentityProvider identityProvider;

    public Long getCompletedGoalsCount() {
        AppUser user = identityProvider.currentIdentity();
        return user.getNoCompletedGoals();
    }

}

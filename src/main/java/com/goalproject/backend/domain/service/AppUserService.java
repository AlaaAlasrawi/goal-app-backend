package com.goalproject.backend.domain.service;

import com.goalproject.backend.application.exception.ResourceNotFoundException;
import com.goalproject.backend.domain.model.AppUser;
import com.goalproject.backend.domain.providers.IdentityProvider;
import com.goalproject.backend.persistence.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final IdentityProvider identityProvider;

    public Long getCompletedGoalsCount() {
        AppUser user = appUserRepository.getById(identityProvider.currentId());
        return user.getNoCompletedGoals();
    }


}

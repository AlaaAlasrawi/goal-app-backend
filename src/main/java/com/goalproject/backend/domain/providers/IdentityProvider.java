package com.goalproject.backend.domain.providers;

import com.goalproject.backend.domain.model.AppUser;
import com.goalproject.backend.persistence.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IdentityProvider {
    private final AppUserRepository appUserRepository;

    public AppUser currentIdentity() {
        return appUserRepository.getById(1L);
    }

    public Long currentId() {
        return 1L;
    }

}

package com.goalproject.backend.persistence.adapter;

import com.goalproject.backend.application.exception.ResourceNotFoundException;
import com.goalproject.backend.domain.mapper.AppUserMapper;
import com.goalproject.backend.domain.model.AppUser;
import com.goalproject.backend.persistence.jpa.AppUserJpaRepository;
import com.goalproject.backend.persistence.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AppUserAdapter implements AppUserRepository {
    private final AppUserJpaRepository appUserJpaRepository;
    private final AppUserMapper appUserMapper;

    @Override
    public AppUser save(AppUser user) {
        return appUserMapper.entityToModel(appUserJpaRepository.save(appUserMapper.modelToEntity(user)));
    }

    @Override
    public AppUser getById(Long id) {
        return appUserMapper.entityToModel(appUserJpaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        ));
    }

    @Override
    public AppUser findByUsername(String username) {
        return appUserMapper.entityToModel(appUserJpaRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("Username not found")
        ));

    }

    @Override
    public boolean isUsernameAlreadyExists(String username) {
        return appUserJpaRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailAlreadyExists(String email) {
        return appUserJpaRepository.existsByEmail(email);
    }
}

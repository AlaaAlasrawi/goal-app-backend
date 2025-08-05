package com.goalproject.backend.persistence.repository;

import com.goalproject.backend.domain.model.AppUser;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository {
    AppUser save(AppUser user);

    AppUser getById(Long userId);
}

package com.goalproject.backend.domain.mapper;

import com.goalproject.backend.domain.model.AppUser;
import com.goalproject.backend.persistence.entity.AppUserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
    AppUserEntity modelToEntity(AppUser appUser);
    AppUser entityToModel(AppUserEntity appUserEntity);
}

package com.goalproject.backend.domain.mapper;

import com.goalproject.backend.application.dtos.auth.RegisterRequest;
import com.goalproject.backend.application.dtos.auth.RegisterResponse;
import com.goalproject.backend.domain.model.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    AppUser requestToModel(RegisterRequest request);
    RegisterResponse modelToResponse(AppUser user);
}

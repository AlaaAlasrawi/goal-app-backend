package com.goalproject.backend.domain.mapper;

import com.goalproject.backend.application.dtos.auth.UserAuthenticationRequest;
import com.goalproject.backend.application.dtos.auth.UserAuthenticationResponse;
import com.goalproject.backend.domain.model.UserAuthentication;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAuthenticationMapper {
    UserAuthentication requestToModel(UserAuthenticationRequest request);
    UserAuthenticationResponse modelToResponse(UserAuthentication user);
}

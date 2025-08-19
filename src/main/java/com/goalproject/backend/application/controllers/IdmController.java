package com.goalproject.backend.application.controllers;

import com.goalproject.backend.application.dtos.auth.RegisterRequest;
import com.goalproject.backend.application.dtos.auth.RegisterResponse;
import com.goalproject.backend.application.dtos.auth.UserAuthenticationRequest;
import com.goalproject.backend.application.dtos.auth.UserAuthenticationResponse;
import com.goalproject.backend.domain.mapper.RegisterMapper;
import com.goalproject.backend.domain.mapper.UserAuthenticationMapper;
import com.goalproject.backend.domain.service.IdmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/idm")
public class IdmController {
    private final IdmService idmService;
    private final RegisterMapper registerMapper;
    private final UserAuthenticationMapper userAuthenticationMapper;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(registerMapper.modelToResponse(idmService.register(registerMapper.requestToModel(request))));
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthenticationResponse> login(@RequestBody UserAuthenticationRequest request) {
        return ResponseEntity.ok(userAuthenticationMapper.modelToResponse(idmService.login(userAuthenticationMapper.requestToModel(request))));
    }
}

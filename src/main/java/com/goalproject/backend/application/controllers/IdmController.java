package com.goalproject.backend.application.controllers;

import com.goalproject.backend.domain.service.IdmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/idm")
public class IdmController {
    private final IdmService idmService;
    private final RegisterMapper registerMapper;
    private final UserAuthenticationMapper userAuthenticationMapper;
}

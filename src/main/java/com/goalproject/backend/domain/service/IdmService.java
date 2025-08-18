package com.goalproject.backend.domain.service;

import com.goalproject.backend.domain.providers.IdentityProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdmService {
    private final IdentityProvider identityProvider;


}

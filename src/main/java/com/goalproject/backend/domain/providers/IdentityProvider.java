package com.goalproject.backend.domain.providers;

import com.goalproject.backend.domain.model.AppUser;
import com.goalproject.backend.domain.service.security.AppUserDetailsService;
import com.goalproject.backend.persistence.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IdentityProvider {
    private final AppUserRepository appUserRepository;
    private final AppUserDetailsService appUserDetailsService;

    public AppUser currentIdentity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            if(authentication.getPrincipal() instanceof UserDetails userDetails) {
                return appUserRepository.findByUsername(userDetails.getUsername());
            }

            if(authentication.getPrincipal() instanceof String username) {
                return appUserRepository.findByUsername(username);
            }
        }

        return null;
    }

    public UserDetails currentIdentityDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            if(authentication.getPrincipal() instanceof UserDetails userDetails) {
                return userDetails;
            }

            if(authentication.getPrincipal() instanceof String username) {
                return appUserDetailsService.loadUserByUsername(username);
            }
        }

        return null;
    }

}

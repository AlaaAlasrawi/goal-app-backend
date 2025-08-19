package com.goalproject.backend.domain.service;

import com.goalproject.backend.application.exception.DuplicateResourceException;
import com.goalproject.backend.application.exception.InvalidCredentialsException;
import com.goalproject.backend.domain.model.AppUser;
import com.goalproject.backend.domain.model.UserAuthentication;
import com.goalproject.backend.domain.service.security.AppUserDetailsService;
import com.goalproject.backend.domain.service.security.JwtService;
import com.goalproject.backend.persistence.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdmService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AppUserRepository appUserRepository;
    private final AppUserDetailsService appUserDetailsService;


    public AppUser register(AppUser user) {
        validateUserInfo(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setNoCompletedGoals(0L);
        return appUserRepository.save(user);
    }

    private void validateUserInfo(AppUser user) {

        if (appUserRepository.isUsernameAlreadyExists(user.getUsername())) {
            throw new DuplicateResourceException("Username already exists");
        }

        if (appUserRepository.isEmailAlreadyExists(user.getEmail())) {
            throw new DuplicateResourceException("email already exists");
        }
    }

    public UserAuthentication login(UserAuthentication userAuthentication) {
        Authentication authentication = getAuthenticationStatus(userAuthentication);
        if (authentication.isAuthenticated()) {
            updateSecurityContextHolder(authentication);
            return buildUserAuthentication(userAuthentication);
        }

        throw new InvalidCredentialsException("Bad credentials, username or password are incorrect");
    }

    private static void updateSecurityContextHolder(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserAuthentication buildUserAuthentication(UserAuthentication userAuthentication) {
        UserDetails user = appUserDetailsService.loadUserByUsername(userAuthentication.getUsername());
        String token = jwtService.generateToken(user);
        userAuthentication.setToken(token);
        return userAuthentication;
    }

    private Authentication getAuthenticationStatus(UserAuthentication endUserAuthentication) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        endUserAuthentication.getUsername(),
                        endUserAuthentication.getPassword()
                )
        );
    }

}

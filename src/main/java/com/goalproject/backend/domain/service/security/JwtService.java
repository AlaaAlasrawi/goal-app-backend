package com.goalproject.backend.domain.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${goaldb.jwt.secretKey}")
    private String secretKey;

    @Value("${goaldb.jwt.validity}")
    private long validity;

    @Value("${goaldb.jwt.authHeader}")
    private String authHeader;

    @Value("${goaldb.jwt.prefix}")
    private String prefix;

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(TimeUnit.DAYS.toMillis(validity))))
                .signWith(generateKey())
                .compact();
    }

    public String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(authHeader);
        if (bearerToken != null && bearerToken.startsWith(prefix.concat(" "))) {
            return bearerToken.substring(7); // remove "Bearer "
        }
        return null;
    }

    public Boolean isTokenExpired(String token) {
        Date expirationDate = getClaimsFromToken(token).getExpiration();
        return expirationDate.before(Date.from(Instant.now()));
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        if (token.isBlank() || userDetails == null) {
            return false;
        }
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token)
                && isSignatureValid(token);
    }

    private SecretKey generateKey() {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isSignatureValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith(generateKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

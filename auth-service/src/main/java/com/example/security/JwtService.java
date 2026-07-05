package com.example.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {

    private static final String ROLE_CLAIM = "role";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    private SecretKey key ;

    @PostConstruct
    public void init() {
        if (secret == null || secret.isBlank()) {
            throw new IllegalStateException("JWT secret is not configured.");
        }
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        log.info("JWT service initialized successfully.");
    }

    /**
     * Generate JWT Token
     */
    public String generateToken(UserDetails userDetails) {
        log.info("Generating JWT token for user: {}", userDetails.getUsername());
        String token = Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .claim(ROLE_CLAIM, userDetails.getAuthorities()
                        .stream()
                        .findFirst()
                        .map(Object::toString)
                        .orElse("ROLE_USER"))
                .signWith(key)
                .compact();
        log.info("JWT token generated successfully for user {}", userDetails.getUsername());
        return token;
    }

    /**
     * Validate JWT Token
     */
    public boolean validateToken(String token, UserDetails userDetails) {

        try {

            Claims claims = extractAllClaims(token);

            String username = claims.getSubject();

            return username.equals(userDetails.getUsername())
                    && claims.getExpiration().after(new Date());

        } catch (JwtException | IllegalArgumentException ex) {

            log.warn("Invalid JWT token: {}", ex.getMessage());

            return false;
        }
    }

    /**
     * Extract Username
     */
    public String extractUsername(String token) {

        log.debug("Extracting username from JWT token");

        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get(ROLE_CLAIM, String.class));
    }

    /**
     * Extract Expiration
     */
    public Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }



    /**
     * Generic Claim Extractor
     */
    public <T> T extractClaim(String token,
                              Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    /**
     * Extract all claims
     */
    private Claims extractAllClaims(String token) {

        log.debug("Extracting JWT claims");

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Check Token Expired
     */
    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }






}

package com.example.filter;

import com.example.exception.InvalidTokenException;
import com.example.security.CustomUserDetailsService;
import com.example.security.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String authHeader = request.getHeader(AUTH_HEADER);
        if (authHeader == null && !authHeader.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = authHeader.substring(BEARER_PREFIX.length());
        try{
            String userEmail = jwtService.extractUsername(jwt);
            if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null)
            {
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);
                if(jwtService.validateToken(jwt, userDetails))
                {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }else {
                log.debug("JWT failed validation for user={}", userEmail);
            }
        }catch (InvalidTokenException ex){
            // Do not throw here — let the request continue unauthenticated so
            // Spring Security's entry point produces a clean 401 JSON response
            // instead of an unhandled exception during filter processing.
            log.debug("Ignoring invalid JWT on {}: {}", request.getRequestURI(), ex.getMessage());
        }catch (Exception ex){
            log.warn("Unexpected error while processing JWT on {}: {}", request.getRequestURI(), ex.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}

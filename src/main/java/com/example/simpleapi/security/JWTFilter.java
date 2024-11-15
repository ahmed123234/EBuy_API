package com.example.simpleapi.security;

import com.example.simpleapi.services.userService.LoginResponse;
import com.example.simpleapi.services.userService.UserServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserServiceImplementation userDetailsService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader =  request.getHeader("Authorization");
        String username;
        String jwt;

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ") ) {
            filterChain.doFilter(request, response);
            log.error("inside do filter internal no authentication");
            return;
        }

        try {
            jwt = authorizationHeader.substring(7);

            username = jwtService.extractUsername(jwt);



        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user=  userDetailsService.loadUserByUsername(username);

            if(jwtService.isValidToken(jwt, user)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        username, user.getPassword(),
                        user.getAuthorities()
                );

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                log.info("inside security context holder");
            }

            filterChain.doFilter(request, response);
        }

        } catch (Exception exception) {
            response.setStatus(403);
            response.setContentType(APPLICATION_JSON_VALUE);

            new ObjectMapper().writeValue(response.getOutputStream(),
                    LoginResponse.builder().error(exception.getMessage()));
            log.info("inside jwt auth filter response status is {}",response.getStatus());
        }

    }
}

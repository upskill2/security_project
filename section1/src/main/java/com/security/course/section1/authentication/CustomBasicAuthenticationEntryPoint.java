package com.security.course.section1.authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence (final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("bank-error-reason", "Authentication Error");
        response.setStatus (HttpStatus.UNAUTHORIZED.value());
        response.setContentType (MediaType.APPLICATION_JSON_VALUE);

        String jsonResponse = String.format ("Custom response,\ntimestamp: {}", LocalDateTime.now ());
        response.getWriter ().write (jsonResponse);
    }
}

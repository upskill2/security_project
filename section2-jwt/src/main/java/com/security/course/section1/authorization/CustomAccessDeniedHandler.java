package com.security.course.section1.authorization;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle (final HttpServletRequest request, final HttpServletResponse response, final AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setHeader("bank-error-reason", "Authorization Error");
        response.setStatus (HttpStatus.FORBIDDEN.value ());
        response.setContentType (MediaType.APPLICATION_JSON_VALUE);

        String jsonResponse = String.format ("Custom response,\n timestamp: %s", LocalDateTime.now ());
        response.getWriter ().write (jsonResponse);
    }
}

package com.eazybytes.eazyschool.hadler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess (final HttpServletRequest request, final HttpServletResponse response,
                                         final Authentication authentication) throws IOException, ServletException {
        log.info ("Login successful for user: {}", authentication.getName ());
        response.sendRedirect ("/dashboard");
    }
}

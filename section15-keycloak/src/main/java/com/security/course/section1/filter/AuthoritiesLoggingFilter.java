package com.security.course.section1.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@Slf4j
public class AuthoritiesLoggingFilter implements Filter {
    @Override
    public void doFilter (final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        if (authentication != null) {
            log.info ("User: " + authentication.getName () + " Authorities: " + authentication.getAuthorities ());
        }
        chain.doFilter (request, response);
    }
}

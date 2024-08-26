package com.security.course.section1.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AuthoritiesLoggingAtFilter implements Filter {
    @Override
    public void doFilter (final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final String user = request.getParameter ("user");
        final String authorities = request.getParameter ("authorities");
        log.info ("Authentication validation is in progress: " + user + " Authorities: " + authorities);
        chain.doFilter (request, response);
    }
}

package com.security.course.section1.filter;

import com.mysql.cj.util.StringUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class RequestValidationFilter implements Filter {

    @Override
    public void doFilter (final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String header = req.getHeader (HttpHeaders.AUTHORIZATION);
        if (header != null) {
            header = header.trim ();
            if (StringUtils.startsWithIgnoreCase (header, "basic ")) {
                final byte[] base64Token = header.substring (6).getBytes (StandardCharsets.UTF_8);//check why need to convert to bytes
                byte[] decoded;

                try {
                    decoded = Base64.getDecoder ().decode (base64Token);
                    final String token = new String (decoded, StandardCharsets.UTF_8);
                    int delim = token.indexOf (":");
                    if(delim==-1){
                        throw new BadCredentialsException("Invalid basic authentication token");
                    }
                    String email = token.substring (0, delim);
                    if(email.toLowerCase ().contains ("test")){
                        resp.setStatus (HttpServletResponse.SC_BAD_REQUEST);
                        return;
                    }
                } catch (Exception e) {
                    throw new BadCredentialsException("Failed basic authentication token");
                }
            }
        }
        chain.doFilter (request,response);
    }
}

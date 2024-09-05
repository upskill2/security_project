package com.security.course.section1.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
public class JwtGeneratorFilter extends OncePerRequestFilter {

    public static final String JWT_SECRET_KEY = "JWT_SECRET";
    public static final String JWT_DEFAULT_SECRET = "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9";
    public static final String JWT_HEADER = "Authorization";

    @Override
    protected void doFilterInternal (final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        final Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        Environment env = getEnvironment ();
        if (env != null) {
            final String secret = env.getProperty (JWT_SECRET_KEY, JWT_DEFAULT_SECRET);
            SecretKey secretKey = Keys.hmacShaKeyFor (secret.getBytes (StandardCharsets.UTF_8));
            final String jwt = Jwts.builder ()
                    .issuer ("Demo")
                    .subject ("JWT Token")
                    .claim ("username", authentication == null ? "1@admin.com" : authentication.getName ())
                    .claim ("authorities", authentication != null ? authentication.getAuthorities ().stream ().map (
                            GrantedAuthority::getAuthority).collect (Collectors.joining (",")) : "ADMIN")
                    .issuedAt (new Date ())
                    .expiration (new Date (new Date ().getTime () + 30000000))
                    .signWith (secretKey)
                    .compact ();
            response.setHeader (JWT_HEADER, jwt);
        }
        filterChain.doFilter (request, response);
    }

    @Override
    protected boolean shouldNotFilter (final HttpServletRequest request) throws ServletException {
        return !request.getServletPath ().equals ("/user");
    }
}

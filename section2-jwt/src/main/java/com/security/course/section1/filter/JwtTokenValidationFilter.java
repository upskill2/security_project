package com.security.course.section1.filter;

import com.security.course.section1.model.Authorities;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.security.course.section1.filter.JwtGeneratorFilter.*;

public class JwtTokenValidationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal (final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader (JWT_HEADER);
        Environment env = getEnvironment ();
        if (jwt != null) {
            try {
                final String key = env.getProperty (JWT_SECRET_KEY, JWT_DEFAULT_SECRET);
                SecretKey secretKey = Keys.hmacShaKeyFor (key.getBytes (StandardCharsets.UTF_8));
                final Claims claims = Jwts
                        .parser ()
                        .verifyWith (secretKey)
                        .build ()
                        .parseSignedClaims (jwt)
                        .getPayload ();
                String username = String.valueOf (claims.get ("username"));
                String authorities = String.valueOf (claims.get ("authorities")) == null ? "ADMIN" : String.valueOf (claims.get ("authorities"));

                Authentication authentication = new UsernamePasswordAuthenticationToken (username, null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList (authorities));
                SecurityContextHolder.getContext ().setAuthentication (authentication);

            } catch (Exception e) {
                throw new BadCredentialsException ("Invalid jwt token received");
            }
        }

        filterChain.doFilter (request, response);
    }

    @Override
    protected boolean shouldNotFilter (final HttpServletRequest request) throws ServletException {
        return request.getServletPath ().equals ("/user");
    }
}

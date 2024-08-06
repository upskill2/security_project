package com.security.course.section1.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BankUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean supports (Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom (authentication));
    }

    @Override
    public Authentication authenticate (Authentication authentication) throws AuthenticationException {

        String username = authentication.getName ();
        String pwd = authentication.getCredentials ().toString ();
        final UserDetails userDetails = userDetailsService.loadUserByUsername (username);
            //+ Custom validation logic
        if (!passwordEncoder.matches (pwd, userDetails.getPassword ())) {
            throw new BadCredentialsException ("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken (pwd, authentication.getCredentials (), authentication.getAuthorities ());
    }


}

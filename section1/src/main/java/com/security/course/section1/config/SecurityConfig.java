package com.security.course.section1.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain (HttpSecurity http) throws Exception {
        http.authorizeHttpRequests ((requests) -> requests
                .requestMatchers ("/notices", "/contacts", "/actuator*", "/error")
                .permitAll ()
                .requestMatchers (
                        "/myAccount", "/myLoans", "/myCards", "/contacts")
                .authenticated ()
                .anyRequest ()
                .denyAll ()
        );
        http.formLogin (withDefaults ());
        http.httpBasic (withDefaults ());
        return http.build ();
    }

    @Bean
    public UserDetailsService userDetailsService () {
        final UserDetails user = User.withUsername ("user").password ("{noop}user").authorities ("read").build ();
        final UserDetails admin = User.withUsername ("admin")
                .password ("{bcrypt}$2a$12$AN28eCW1X51cSYHyJWYrcewLCyzKKKs9DSHv.7/ANvmmXTwHeZAm2")
                .authorities ("admin").build ();
        return new InMemoryUserDetailsManager (user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder ();
    }

    @Bean
    @ConditionalOnProperty (
            matchIfMissing = true,
            prefix = "spring.security",
            value = "check-compromised-passwords",
            havingValue = "true")
    public CompromisedPasswordChecker compromisedPasswordChecker () {
        return new HaveIBeenPwnedRestApiPasswordChecker ();
    }


}

package com.security.course.section1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.security.course.section1.authentication.CustomBasicAuthenticationEntryPoint;
import com.security.course.section1.authorization.CustomAccessDeniedHandler;
import com.security.course.section1.filter.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain (HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler handler = new CsrfTokenRequestAttributeHandler ();
        JwtAuthenticationConverter jwtC = new JwtAuthenticationConverter ();
        jwtC.setJwtGrantedAuthoritiesConverter (new KeyCloakRoleConverter ());

        http
                .cors (c -> c.configurationSource (new CorsConfigurationSource () {
                    @Override
                    public CorsConfiguration getCorsConfiguration (final HttpServletRequest request) {
                        CorsConfiguration corsConfiguration = new CorsConfiguration ();
                        corsConfiguration.addAllowedOriginPattern ("*");
                        //corsConfiguration.setAllowedOrigins (Collections.singletonList ("http://localhost:4200/"));
                        corsConfiguration.setAllowedMethods (Collections.singletonList ("*"));
                        corsConfiguration.setAllowedHeaders (Collections.singletonList ("*"));
                        corsConfiguration.setAllowCredentials (true);
                        corsConfiguration.setMaxAge (3600L);
                        corsConfiguration.setExposedHeaders (Arrays.asList ("Authorization"));
                        return corsConfiguration;
                    }
                }))
                .sessionManagement (s -> s.invalidSessionUrl ("/invalidSession")
                        .sessionFixation (s1 -> s1.newSession ())
                        .maximumSessions (10)
                        .maxSessionsPreventsLogin (true)
                        .expiredUrl ("/expireUrl"))
                .sessionManagement (s -> s.sessionCreationPolicy (SessionCreationPolicy.STATELESS))
                .requiresChannel (r -> r.anyRequest ()
                        .requiresInsecure ())
                .csrf (c -> c.csrfTokenRequestHandler (handler)
                        .ignoringRequestMatchers ("/notices", "/contact", "/actuator*", "/error",
                                "/registerUser", "/invalidSession", "/expireUrl")
                        .csrfTokenRepository (CookieCsrfTokenRepository.withHttpOnlyFalse ()))
                .addFilterAfter (new CsrfCookieFilter (), BasicAuthenticationFilter.class)
                .authorizeHttpRequests ((requests) -> requests
                        .requestMatchers ("/notices", "/contact", "/actuator*", "/error",
                                "/registerUser", "/invalidSession", "/expireUrl", "/apiLogin")
                        .permitAll ()
                        .requestMatchers ("/myBalance", "/myLoans").hasAnyRole ("USER", "ADMIN")
                        .requestMatchers (
                                "/myAccount", "/myCards", "/user")
                        .authenticated ()
                );
        http.oauth2ResourceServer (o -> o.jwt (j -> j.jwtAuthenticationConverter (jwtC)));
        http.exceptionHandling (e -> e.accessDeniedHandler (new CustomAccessDeniedHandler ()));

        return http.build ();
    }

}

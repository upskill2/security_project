package com.security.course.section14.controller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Value ("${spring.security.oauth2.client.registration.github.client-id}")
    private String clientId;

    @Value ("${spring.security.oauth2.client.registration.github.client-secret}")
    private String clientSecret;

    @Bean
    SecurityFilterChain defaultSecurityChain (HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests (authorizeRequests ->
                        authorizeRequests
                                .requestMatchers ("/secure")
                                .authenticated ()
                                .anyRequest ().permitAll ()
                )
                .formLogin (Customizer.withDefaults ())
                .oauth2Login (Customizer.withDefaults ()).build ();
    }

    @Bean
    ClientRegistrationRepository clientRegistrationRepository () {
        return new InMemoryClientRegistrationRepository (githubClientRegistration (), faacebookClientRegistration ());
    }


    private ClientRegistration githubClientRegistration () {
        return CommonOAuth2Provider.GITHUB
                .getBuilder ("github")
                .clientId (clientId)
                .clientSecret (clientSecret)
                .build ();
    }

    private ClientRegistration faacebookClientRegistration () {
        return CommonOAuth2Provider.FACEBOOK
                .getBuilder ("fecebook")
                .clientId ("facebook-client-id")
                .clientSecret ("facebook-client-secret")
                .build ();
    }


}

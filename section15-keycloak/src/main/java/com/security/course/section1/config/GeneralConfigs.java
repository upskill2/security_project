package com.security.course.section1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class GeneralConfigs {

    @Bean
    public PasswordEncoder passwordEncoder () {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder ();
    }

    @Bean
    public ObjectMapper objectMapper () {
        ObjectMapper mapper = new ObjectMapper ();
        mapper.registerModule (new JavaTimeModule ());
        return mapper;
    }
}

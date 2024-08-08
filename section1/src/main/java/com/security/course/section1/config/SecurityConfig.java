package com.security.course.section1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.course.section1.authentication.CustomBasicAuthenticationEntryPoint;
import com.security.course.section1.authorization.CustomAccessDeniedHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain (HttpSecurity http) throws Exception {
        http
               // .cors (withDefaults ())
                .sessionManagement (s -> s.invalidSessionUrl ("/invalidSession")
                        .sessionFixation (s1 -> s1.newSession ())
                        .maximumSessions (1)
                        .maxSessionsPreventsLogin (true)
                        .expiredUrl ("/expireUrl"))
                .requiresChannel (r -> r.anyRequest ()
                        .requiresInsecure ())
                       //.requiresSecure ())
                .csrf (AbstractHttpConfigurer::disable)
                .authorizeHttpRequests ((requests) -> requests
                        .requestMatchers ("/notices", "/contacts", "/actuator*", "/error",
                                "/registerUser", "/invalidSession", "/expireUrl")
                        .permitAll ()
                        .requestMatchers (
                                "/myAccount", "/myLoans", "/myCards", "/contacts")
                        .authenticated ()
                        .anyRequest ()
                        .denyAll ()
                );
        http.formLogin (withDefaults ());
        http.httpBasic (a -> a.authenticationEntryPoint (new CustomBasicAuthenticationEntryPoint ()));
        http.exceptionHandling (e -> e.accessDeniedHandler (new CustomAccessDeniedHandler ()));

        return http.build ();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer () {
        return new WebMvcConfigurer () {
            @Override
            public void addCorsMappings (CorsRegistry registry) {
                registry.addMapping ("/**")
                        .allowedOrigins ("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH", "TRACE")
                        .allowedHeaders("Accept", "X-Access-Token", "X-Application-Name", "X-Request-Sent-Time")
                        .allowCredentials (false)
                        .maxAge (3600);
            }
        };
    }

    @Bean
    public ObjectMapper objectMapper () {
        return new ObjectMapper ();
    }

/*    @Bean
    public UserDetailsService userDetailsService () {
        final UserDetails user = User.withUsername ("user").password ("{noop}user").authorities ("read").build ();
        final UserDetails admin = User.withUsername ("admin")
                .password ("{bcrypt}$2a$12$AN28eCW1X51cSYHyJWYrcewLCyzKKKs9DSHv.7/ANvmmXTwHeZAm2")
                .authorities ("admin").build ();
        return new InMemoryUserDetailsManager (user, admin);
    }*/

/*    @Bean
    public UserDetailsService userDetailsService (DataSource dataSource) {
        return new JdbcUserDetailsManager (dataSource);
    }*/

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

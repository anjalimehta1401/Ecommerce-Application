package com.youtube.demo.ecoomerce.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //TODO: Proper authentication.
        http.csrf(csrf -> csrf.disable());
        http.cors(cors -> cors.disable());

        // Allow all requests without authentication (not recommended for production)
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());

        // Build the SecurityFilterChain
        return http.build();
    }

}

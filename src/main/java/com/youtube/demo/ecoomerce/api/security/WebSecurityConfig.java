package com.youtube.demo.ecoomerce.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class WebSecurityConfig {

    private JWTRequestFilter jwtRequestFilter;

    public WebSecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //TODO: Proper authentication.
        http.csrf(csrf -> csrf.disable());
        http.cors(cors -> cors.disable());

        http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
        // Allow all requests without authentication (not recommended for production)
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/product", "/auth/register", "/auth/login","/auth/verified").permitAll()
                .anyRequest().authenticated());

        // Build the SecurityFilterChain
        return http.build();
    }

}

package com.Springboot.Student.Management.Project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class StudentSecurity {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails Avinash = User.builder()
                .username("Avinash")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();
        UserDetails Krishna = User.builder()
                .username("Krishna")
                .password("{noop}test123")
                .roles("MANAGER")
                .build();
        return new InMemoryUserDetailsManager(Avinash,Krishna);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(Configurer ->
               Configurer.
                       requestMatchers(HttpMethod.GET,"/api/students").hasRole("EMPLOYEE")
                       .requestMatchers(HttpMethod.GET,"/api/students/**").hasRole("EMPLOYEE")
                       .requestMatchers(HttpMethod.POST,"/api/students").hasRole("MANAGER")
                       .requestMatchers(HttpMethod.PUT,"/api/students").hasRole("MANAGER")
                       .requestMatchers(HttpMethod.DELETE,"/api/students/**").hasRole("MANAGER")
                       .requestMatchers(HttpMethod.DELETE,"/api/students").hasRole("MANAGER"));
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}

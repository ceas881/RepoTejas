/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tejaupvc.security;

import org.codehaus.plexus.component.annotations.Configuration;
// Controladores
import org.springframework.web.bind.annotation.*;
// Servicios
import org.springframework.stereotype.Service;
// Inyección de dependencias
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



/**
 *
 * @author AMAF
 */

@EnableWebSecurity
public class SecurityConfig {
  @Autowired JwtFilter jwtFilter;

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
       .authorizeHttpRequests(auth -> auth
         .requestMatchers("/", "/home", "/css/**", "/js/**", "/images/**").permitAll()
         .requestMatchers(HttpMethod.GET, "/api/productos/**", "/api/accesorios/**").permitAll()
         .requestMatchers("/api/contacto/**", "/api/pagos/**").permitAll()
         .requestMatchers("/api/auth/**").permitAll()
         .anyRequest().authenticated()
       )
       .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  // Usuario demo en memoria para login básico
  @Bean
  UserDetailsService uds() {
    var u = User.withUsername("admin").password("{noop}admin123").roles("ADMIN").build();
    return new InMemoryUserDetailsManager(u);
  }
}

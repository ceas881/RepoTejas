/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tejaupvc.security;

// Controladores
import org.springframework.web.bind.annotation.*;
// Servicios
import org.springframework.stereotype.Service;
// Inyección de dependencias
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

/**
 *
 * @author AMAF
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired AuthenticationManagerBuilder authBuilder;
  @Autowired JwtUtil jwt;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthRequest req) {
    try {
      var manager = authBuilder.getObject();
      manager.authenticate(new UsernamePasswordAuthenticationToken(req.username, req.password));
      return ResponseEntity.ok(new AuthResponse(jwt.generate(req.username)));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
}

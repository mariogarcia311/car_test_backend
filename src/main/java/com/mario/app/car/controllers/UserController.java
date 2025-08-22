package com.mario.app.car.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mario.app.car.entities.User;
import com.mario.app.car.repositories.UserRepository;
import com.mario.app.car.services.UserService;
import com.mario.app.car.dto.AuthResponse;
import com.mario.app.car.dto.LoginRequest;
import com.mario.app.car.dto.RegisterRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterRequest request) {
    AuthResponse response = userService.register(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginRequest request) {
    AuthResponse response = userService.login(request);
    return ResponseEntity.ok(response);
  }

}

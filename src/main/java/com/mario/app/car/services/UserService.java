package com.mario.app.car.services;

import java.util.List;

import com.mario.app.car.entities.User;
import com.mario.app.car.dto.AuthResponse;
import com.mario.app.car.dto.LoginRequest;
import com.mario.app.car.dto.RegisterRequest;

public interface UserService {

  List<User> findAll();

  User save(User user);

  boolean existsByUsername(String username);

  AuthResponse register(RegisterRequest request);

  AuthResponse login(LoginRequest request);
}

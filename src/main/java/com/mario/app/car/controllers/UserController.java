package com.mario.app.car.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mario.app.car.entities.User;
import com.mario.app.car.repositories.UserRepository;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/register")
  public String registerUser(@RequestBody User user) {
    userRepository.save(user);
    return "Hola Mundo";
  }

  @PostMapping("/login")
  public String loginUser(@RequestBody User user) {
    userRepository.findByUsername(user.getUsername());
    return "Hola Mundo";
  }

}

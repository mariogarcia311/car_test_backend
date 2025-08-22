package com.mario.app.car.services;

import java.util.List;

import com.mario.app.car.entities.User;

public interface UserService {

  List<User> findAll();

  User save(User user);

  boolean existsByUsername(String username);
}

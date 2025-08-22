package com.mario.app.car.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.mario.app.car.entities.User;
import com.mario.app.car.repositories.UserRepository;
import com.mario.app.car.dto.AuthResponse;
import com.mario.app.car.dto.LoginRequest;
import com.mario.app.car.dto.RegisterRequest;
import com.mario.app.car.exceptions.AuthenticationException;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtService jwtService;

  @Override
  public List<User> findAll() {
    return (List<User>) userRepository.findAll();
  }

  private User saveUserWithoutValidation(String username, String password) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    return userRepository.save(user);
  }

  @Override
  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  @Override
  public User save(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public AuthResponse register(RegisterRequest request) {
    System.out.println("registeeeeeer" + existsByUsername(request.getUsername()));
    if (existsByUsername(request.getUsername())) {
      throw new AuthenticationException("El nombre de usuario ya existe");
    }

    User savedUser = saveUserWithoutValidation(request.getUsername(),
        request.getPassword());

    String token = jwtService.generateToken(savedUser.getUsername(), savedUser.getId());

    return new AuthResponse(token, "Usuario registrado exitosamente", savedUser.getUsername(), savedUser.getId());
  }

  @Override
  public AuthResponse login(LoginRequest request) {
    Optional<User> userOptional = userRepository.findByUsername(request.getUsername());

    if (userOptional.isEmpty()) {
      throw new AuthenticationException("Usuario o contraseña incorrecta");
    }

    User user = userOptional.get();

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new AuthenticationException("Usuario o contraseña incorrecta");
    }

    String token = jwtService.generateToken(user.getUsername(), user.getId());

    return new AuthResponse(token, "Login exitoso", user.getUsername(), user.getId());
  }
}

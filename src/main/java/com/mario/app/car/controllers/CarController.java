package com.mario.app.car.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mario.app.car.dto.CarRequest;
import com.mario.app.car.dto.CarResponse;
import com.mario.app.car.entities.User;
import com.mario.app.car.repositories.UserRepository;
import com.mario.app.car.services.CarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

  @Autowired
  private CarService carService;

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public ResponseEntity<List<CarResponse>> getAllCars() {
    User currentUser = getCurrentUser();
    List<CarResponse> cars = carService.findAllByUser(currentUser);
    return ResponseEntity.ok(cars);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CarResponse> getCarById(@PathVariable Long id) {
    User currentUser = getCurrentUser();
    CarResponse car = carService.findByIdAndUser(id, currentUser);
    return ResponseEntity.ok(car);
  }

  @PostMapping
  public ResponseEntity<CarResponse> createCar(@Valid @RequestBody CarRequest request) {
    User currentUser = getCurrentUser();
    CarResponse car = carService.save(request, currentUser);
    return ResponseEntity.ok(car);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CarResponse> updateCar(@PathVariable Long id, @Valid @RequestBody CarRequest request) {
    User currentUser = getCurrentUser();
    CarResponse car = carService.update(id, request, currentUser);
    return ResponseEntity.ok(car);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
    User currentUser = getCurrentUser();
    carService.deleteByIdAndUser(id, currentUser);
    return ResponseEntity.noContent().build();
  }

  private User getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getPrincipal() instanceof User) {
      return (User) authentication.getPrincipal();
    }
    throw new RuntimeException("Usuario no autenticado");
  }
}

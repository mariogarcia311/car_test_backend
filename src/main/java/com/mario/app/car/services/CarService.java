package com.mario.app.car.services;

import java.util.List;

import com.mario.app.car.dto.CarRequest;
import com.mario.app.car.dto.CarResponse;
import com.mario.app.car.entities.User;

public interface CarService {

  List<CarResponse> findAllByUser(User user);

  CarResponse findByIdAndUser(Long id, User user);

  CarResponse save(CarRequest request, User user);

  CarResponse update(Long id, CarRequest request, User user);

  void deleteByIdAndUser(Long id, User user);
}

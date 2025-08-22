package com.mario.app.car.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.mario.app.car.entities.Car;
import com.mario.app.car.entities.User;

public interface CarRepository extends CrudRepository<Car, Long> {

  List<Car> findByUser(User user);

  Optional<Car> findByIdAndUser(Long id, User user);

  boolean existsByLicensePlate(String licensePlate);

  boolean existsByLicensePlateAndIdNot(String licensePlate, Long id);
}

package com.mario.app.car.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mario.app.car.dto.CarRequest;
import com.mario.app.car.dto.CarResponse;
import com.mario.app.car.entities.Car;
import com.mario.app.car.entities.User;
import com.mario.app.car.repositories.CarRepository;
import com.mario.app.car.exceptions.BusinessException;

@Service
public class CarServiceImpl implements CarService {

  @Autowired
  private CarRepository carRepository;

  @Override
  public List<CarResponse> findAllByUser(User user) {
    List<Car> cars = carRepository.findByUser(user);
    return cars.stream()
        .map(this::convertToResponse)
        .collect(Collectors.toList());
  }

  @Override
  public CarResponse findByIdAndUser(Long id, User user) {
    Car car = carRepository.findByIdAndUser(id, user)
        .orElseThrow(() -> new BusinessException("Auto no encontrado"));
    return convertToResponse(car);
  }

  @Override
  public CarResponse save(CarRequest request, User user) {
    if (carRepository.existsByLicensePlate(request.getLicensePlate())) {
      throw new BusinessException("Ya existe un auto con esa placa");
    }

    Car car = new Car(
        request.getBrand(),
        request.getModel(),
        request.getYear(),
        request.getLicensePlate(),
        request.getColor(),
        user);

    Car savedCar = carRepository.save(car);
    return convertToResponse(savedCar);
  }

  @Override
  public CarResponse update(Long id, CarRequest request, User user) {
    Car car = carRepository.findByIdAndUser(id, user)
        .orElseThrow(() -> new BusinessException("Auto no encontrado"));

    if (carRepository.existsByLicensePlateAndIdNot(request.getLicensePlate(), id)) {
      throw new BusinessException("Ya existe otro auto con esa placa");
    }

    car.setBrand(request.getBrand());
    car.setModel(request.getModel());
    car.setYear(request.getYear());
    car.setLicensePlate(request.getLicensePlate());
    car.setColor(request.getColor());

    Car updatedCar = carRepository.save(car);
    return convertToResponse(updatedCar);
  }

  @Override
  public void deleteByIdAndUser(Long id, User user) {
    Car car = carRepository.findByIdAndUser(id, user)
        .orElseThrow(() -> new BusinessException("Auto no encontrado"));
    carRepository.delete(car);
  }

  private CarResponse convertToResponse(Car car) {
    return new CarResponse(
        car.getId(),
        car.getBrand(),
        car.getModel(),
        car.getYear(),
        car.getLicensePlate(),
        car.getColor(),
        car.getUser().getId());
  }
}

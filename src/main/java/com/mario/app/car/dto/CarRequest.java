package com.mario.app.car.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public class CarRequest {

  @NotBlank(message = "La marca es obligatoria")
  private String brand;

  @NotBlank(message = "El modelo es obligatorio")
  private String model;

  @NotNull(message = "El año es obligatorio")
  @Min(value = 1900, message = "El año debe ser mayor a 1900")
  @Max(value = 2026, message = "El año no puede ser mayor a 2026")
  private Integer year;

  @NotBlank(message = "La placa es obligatoria")
  private String licensePlate;

  @NotBlank(message = "El color es obligatorio")
  private String color;

  public CarRequest() {
  }

  public CarRequest(String brand, String model, Integer year, String licensePlate, String color) {
    this.brand = brand;
    this.model = model;
    this.year = year;
    this.licensePlate = licensePlate;
    this.color = color;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }
}

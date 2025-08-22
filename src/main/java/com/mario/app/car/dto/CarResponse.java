package com.mario.app.car.dto;

public class CarResponse {

  private Long id;
  private String brand;
  private String model;
  private Integer year;
  private String licensePlate;
  private String color;
  private Long userId;

  public CarResponse() {
  }

  public CarResponse(Long id, String brand, String model, Integer year, String licensePlate, String color,
      Long userId) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.year = year;
    this.licensePlate = licensePlate;
    this.color = color;
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}

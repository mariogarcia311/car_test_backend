package com.mario.app.car.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {

  @NotBlank(message = "El nombre de usuario es obligatorio")
  @Size(min = 4, max = 50, message = "El nombre de usuario debe tener entre 4 y 50 caracteres")
  private String username;

  @NotBlank(message = "La contraseña es obligatoria")
  @Size(min = 6, max = 50, message = "La contraseña debe tener entre 6 y 50 caracteres")
  private String password;

  public LoginRequest() {
  }

  public LoginRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

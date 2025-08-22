package com.mario.app.car.dto;

public class AuthResponse {
  private String token;
  private String message;
  private String username;
  private Long id;

  public AuthResponse() {
  }

  public AuthResponse(String token, String message, String username, Long id) {
    this.token = token;
    this.message = message;
    this.username = username;
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}

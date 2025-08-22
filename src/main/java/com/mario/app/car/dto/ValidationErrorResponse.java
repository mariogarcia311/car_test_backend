package com.mario.app.car.dto;

public class ValidationErrorResponse {
  private String message;

  public ValidationErrorResponse() {
  }

  public ValidationErrorResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

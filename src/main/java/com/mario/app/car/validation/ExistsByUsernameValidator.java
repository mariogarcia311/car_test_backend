package com.mario.app.car.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mario.app.car.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsByUsernameValidator implements ConstraintValidator<ExistsByUsername, String> {

  @Autowired
  private UserService userService;

  @Override
  public boolean isValid(String username, ConstraintValidatorContext context) {
    if (userService == null) {
      return true;
    }

    return !userService.existsByUsername(username);
  }
}

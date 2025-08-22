package com.mario.app.car.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsByUsernameValidator.class)
public @interface ExistsByUsername {

  String message() default "El username ya existe";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}

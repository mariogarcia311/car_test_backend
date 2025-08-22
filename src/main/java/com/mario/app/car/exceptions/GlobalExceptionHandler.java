package com.mario.app.car.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.TransactionSystemException;

import com.mario.app.car.dto.AuthResponse;
import com.mario.app.car.dto.ValidationErrorResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<AuthResponse> handleAuthenticationException(AuthenticationException ex) {
    AuthResponse response = new AuthResponse(null, ex.getMessage(), null, null);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
    String errorMessage = ex.getBindingResult().getFieldErrors().stream()
        .findFirst()
        .map(error -> error.getDefaultMessage())
        .orElse("Error de validación");

    ValidationErrorResponse response = new ValidationErrorResponse(errorMessage);
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ValidationErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
    String errorMessage = ex.getConstraintViolations().stream()
        .findFirst()
        .map(ConstraintViolation::getMessage)
        .orElse("Error de validación");

    ValidationErrorResponse response = new ValidationErrorResponse(errorMessage);
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(TransactionSystemException.class)
  public ResponseEntity<ValidationErrorResponse> handleTransactionSystemException(TransactionSystemException ex) {
    if (ex.getCause() != null && ex.getCause().getCause() instanceof ConstraintViolationException) {
      ConstraintViolationException cve = (ConstraintViolationException) ex.getCause().getCause();
      String errorMessage = cve.getConstraintViolations().stream()
          .findFirst()
          .map(ConstraintViolation::getMessage)
          .orElse("Error de validación en la base de datos");

      ValidationErrorResponse response = new ValidationErrorResponse(errorMessage);
      return ResponseEntity.badRequest().body(response);
    }

    ValidationErrorResponse response = new ValidationErrorResponse("Error en la transacción de la base de datos");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ValidationErrorResponse> handleDataIntegrityViolationException(
      DataIntegrityViolationException ex) {
    String errorMessage = "Error de integridad de datos";

    if (ex.getMessage() != null && ex.getMessage().contains("username")) {
      errorMessage = "El nombre de usuario ya existe";
    } else if (ex.getMessage() != null && ex.getMessage().contains("constraint")) {
      errorMessage = "Datos duplicados o inválidos";
    }

    ValidationErrorResponse response = new ValidationErrorResponse(errorMessage);
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ValidationErrorResponse> handleBusinessException(BusinessException ex) {
    ValidationErrorResponse response = new ValidationErrorResponse(ex.getMessage());
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ValidationErrorResponse> handleRuntimeException(RuntimeException ex) {
    String errorMessage = ex.getMessage();

    if (errorMessage == null || errorMessage.trim().isEmpty()) {
      errorMessage = "Error en la operación";
    }

    ValidationErrorResponse response = new ValidationErrorResponse(errorMessage);
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ValidationErrorResponse> handleGenericException(Exception ex) {
    ValidationErrorResponse response = new ValidationErrorResponse("Error interno del servidor");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}

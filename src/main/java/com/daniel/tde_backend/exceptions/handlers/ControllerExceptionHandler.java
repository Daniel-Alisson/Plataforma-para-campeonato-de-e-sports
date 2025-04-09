package com.daniel.tde_backend.exceptions.handlers;

import com.daniel.tde_backend.exceptions.CustomError;
import com.daniel.tde_backend.exceptions.DatabaseException;
import com.daniel.tde_backend.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(e.getMessage(), Instant.now(), request.getRequestURI(), status.value());
        return ResponseEntity.status(status).body(err);
    }

    public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(e.getMessage(), Instant.now(), request.getRequestURI(), status.value());
        return ResponseEntity.status(status).body(err);
    }
}

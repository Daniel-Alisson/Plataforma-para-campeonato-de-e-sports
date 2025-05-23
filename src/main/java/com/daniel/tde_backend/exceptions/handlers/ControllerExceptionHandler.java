package com.daniel.tde_backend.exceptions.handlers;

import com.daniel.tde_backend.exceptions.*;
import com.daniel.tde_backend.exceptions.InvalidInscricaoException;
import com.daniel.tde_backend.exceptions.custom.CustomError;
import com.daniel.tde_backend.exceptions.custom.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        CustomError err = new CustomError(e.getMessage(), Instant.now(), request.getRequestURI(), status.value());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError("Dados inválidos", Instant.now(), request.getRequestURI(), status.value());
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<CustomError> emailInvalido(InvalidEmailException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(e.getMessage(), Instant.now(), request.getRequestURI(), status.value());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<CustomError> dataInvalida(InvalidDataException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(e.getMessage(), Instant.now(), request.getRequestURI(), status.value());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidInscricaoException.class)
    public ResponseEntity<CustomError> argumentoInvalido(InvalidInscricaoException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(e.getMessage(), Instant.now(), request.getRequestURI(), status.value());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidAccessException.class)
    public ResponseEntity<CustomError> acessoInvalido(InvalidAccessException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        CustomError err = new CustomError(e.getMessage(), Instant.now(), request.getRequestURI(), status.value());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidPromotionException.class)
    public ResponseEntity<CustomError> promocaoInvalida(InvalidPromotionException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        CustomError err = new CustomError(e.getMessage(), Instant.now(), request.getRequestURI(), status.value());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(VagasEsgotadasException.class)
    public ResponseEntity<CustomError> vagasEsgotadas(VagasEsgotadasException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(e.getMessage(), Instant.now(), request.getRequestURI(), status.value());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ClosedInscricaoException.class)
    public ResponseEntity<CustomError> inscricoesEncerradas(ClosedInscricaoException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(e.getMessage(), Instant.now(), request.getRequestURI(), status.value());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InsufficientPlayersException.class)
    public ResponseEntity<CustomError> jogadoresInsuficientes(InsufficientPlayersException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(e.getMessage(), Instant.now(), request.getRequestURI(), status.value());
        return ResponseEntity.status(status).body(err);
    }
}

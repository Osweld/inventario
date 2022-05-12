package com.oswelddev.inventario.exceptions;


import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerExceptionhelper {

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class, NoSuchElementException.class, EmptyResultDataAccessException.class})
    ResponseEntity<ErrorResponse> HandleNotFoundExceptions(Exception ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ErrorResponse(new Date(),status.value(), status.name(), ex.getMessage(),request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    ResponseEntity<ErrorResponse> HandleBadRequestExceptions(DataIntegrityViolationException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(new ErrorResponse(new Date(),status.value(), status.name(), ex.getMessage(),request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    ResponseEntity<ErrorResponse> HandleValidationsExceptions(ConstraintViolationException ex,WebRequest request) {
        Map<String, String> validations = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(new ErrorResponse(new Date(), status.value(), status.name(), ex.getMessage(), request.getDescription(false), validations), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> HandleValidationsExceptions(MethodArgumentNotValidException ex,WebRequest request) {
        Map<String, String> validations = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        System.out.println("gg");
        for (FieldError fieldError : ex.getFieldErrors()) {
            validations.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(new ErrorResponse(new Date(), status.value(), status.name(), ex.getMessage(), request.getDescription(false), validations), HttpStatus.BAD_REQUEST);
    }

}

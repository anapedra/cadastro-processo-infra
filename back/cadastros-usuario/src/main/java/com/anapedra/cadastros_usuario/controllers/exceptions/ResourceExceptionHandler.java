package com.anapedra.cadastros_usuario.controllers.exceptions;

import com.anapedra.cadastros_usuario.services.exceptions.DatabaseException;
import com.anapedra.cadastros_usuario.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler{


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status= HttpStatus.NOT_FOUND;
        StandardError errer=new StandardError(Instant.now(),status.value(),error, e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(errer);
    }
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> dataBase(DatabaseException e, HttpServletRequest request){
        String error = "DataBase error";
        HttpStatus status= HttpStatus.BAD_REQUEST;
        StandardError errer=new StandardError(Instant.now(),status.value(),error, e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(errer);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
        String error = "Validation Exception";
        HttpStatus status= HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError errer=new ValidationError(Instant.now(),status.value(),error, e.getMessage(),request.getRequestURI());

        for(FieldError f:e.getBindingResult().getFieldErrors()){
            errer.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(errer);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> handleConstraintViolation(DataIntegrityViolationException ex) {
        String message = "Violação de integridade: dado duplicado ou inválido.";
        StandardError err = new StandardError(Instant.now(), 400, "Database Error", message, "/api/users");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    /*
    @ExceptionHandler(ForbiddenException.class )
    public ResponseEntity<OAuthCustomError> forbidden(ForbiddenException e, HttpServletRequest request){
        OAuthCustomError  errer=new OAuthCustomError("Forbidden", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errer);
    }
    @ExceptionHandler(UnauthorizedException.class )
    public ResponseEntity<OAuthCustomError> Unauthorized(UnauthorizedException e, HttpServletRequest request){
        OAuthCustomError  errer=new OAuthCustomError("Unauthorized", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errer);
    }

     */
}

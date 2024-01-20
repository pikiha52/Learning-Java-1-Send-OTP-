package com.learn_java.learning.global.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learn_java.learning.app.AppResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppResponse<List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        final AppResponse<List<String>> response = new AppResponse<>();
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setErrorMessage(errors.get(0));
        response.setData(null);
        response.setMessage("Bad request!");
        return ResponseEntity.badRequest().body(response);
    }
}

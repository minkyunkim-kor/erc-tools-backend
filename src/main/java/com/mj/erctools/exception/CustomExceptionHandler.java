package com.mj.erctools.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.mj.erctools")
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customExceptionHandler(CustomException exception) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(exception.getError().getMessage()), exception.getError().getStatus());
    }
}

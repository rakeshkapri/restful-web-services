package com.in28minutes.rest.webservices.restfulwebservices.controller;

import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class CustomResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponse expResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(expResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponse expResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(expResponse, HttpStatus.NOT_FOUND);
    }
}

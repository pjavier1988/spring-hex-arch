package com.demo.springdemo.common.exceptions;

import com.demo.springdemo.common.payloads.ErrorResponseObject;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponseObject> handleItemNotFoundException(
            Exception exception, WebRequest request
    ){

        ErrorResponseObject errorResponseObject = new ErrorResponseObject(exception.getMessage(),"",request.getContextPath());
        return new ResponseEntity<>(errorResponseObject, HttpStatus.NOT_FOUND);
    }
}

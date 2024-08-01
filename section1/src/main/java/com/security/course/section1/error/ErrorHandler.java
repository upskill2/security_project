package com.security.course.section1.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException (final NoResourceFoundException ex,
                                                                     final HttpHeaders headers,
                                                                     final HttpStatusCode status,
                                                                     final WebRequest request) {
        return ResponseEntity
                .badRequest ()
                .body ("Resource not found");
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable (final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        return  ResponseEntity
                .badRequest ()
                .body (ex.getMessage ());
    }




}

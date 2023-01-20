package com.kitaplik.libraryservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err -> errors.put(((FieldError)err).getField(), err.getDefaultMessage()));
        errors.put("error","Validation Error(s)");
        errors.put("httpstatus", HttpStatus.BAD_REQUEST);
        errors.put("timestamp", new Date().toString());
        return ResponseEntity.badRequest().body(errors);

    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleBookNotFoundException(BookNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getExceptionMessage());
    }

    @ExceptionHandler(LibraryNotFoundException.class)
    public ResponseEntity<Object> handleLibraryNotFoundException(LibraryNotFoundException exception) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("httpstatus", HttpStatus.NOT_FOUND);
        errors.put("timestamp", new Date().toString());
        errors.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

}

package com.punyadev.spring.validate.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNoValidException(MethodArgumentNotValidException exception) {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        exception.getFieldErrors().stream().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }


    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<Object> employeeNotFoundException(EmployeeNotFoundException exception) {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("errorMessage", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

}

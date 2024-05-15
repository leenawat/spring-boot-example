package com.punyadev.spring.validate.employee.controller;

import com.punyadev.spring.validate.employee.exception.EmployeeNotFoundException;
import com.punyadev.spring.validate.employee.interfaces.FirstValidate;
import com.punyadev.spring.validate.employee.model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
@Validated
public class EmployeeController {

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody @Valid Employee employee) {
        // TODO use Empoyee service for insert to db;
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Employee saved successfully");
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody @Validated(FirstValidate.class) Employee employee) {
        // TODO use Empoyee service for insert to db;
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Employee saved successfully");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> save(@PathVariable Long id){
        throw new EmployeeNotFoundException("Employee not found for id " + id);
    }
}

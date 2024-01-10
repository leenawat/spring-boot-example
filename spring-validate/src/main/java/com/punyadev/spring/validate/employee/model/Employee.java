package com.punyadev.spring.validate.employee.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;

    @NotEmpty(message = "Name can not be empty")
    @Size(min = 5, max = 20, message = "Name length should be between 5 and 20")
    private String name;

    @Email(message = "Email is not valid")
    private String email;

    @Min(value = 18, message = "Age must be between 18 and 30")
    @Max(value = 30, message = "Age must be between 18 and 30")
    private Integer age;
}

package com.example.employee_registration.model.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddEmployeeRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private Long departmentId;

    @NotNull
    @Positive
    private Double salary;

    @NotBlank
    private String role;
}

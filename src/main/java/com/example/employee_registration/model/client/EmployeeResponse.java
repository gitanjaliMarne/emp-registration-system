package com.example.employee_registration.model.client;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeResponse {

    private Long id;
    private String name;
    private String email;
    private String address;
    private String role;
    private Long departmentId;
    private String departmentName;
    private double salary;
}


package com.example.employee_registration.controller;

import com.example.employee_registration.entity.Department;
import com.example.employee_registration.model.client.DepartmentResponse;
import com.example.employee_registration.model.client.EmployeeResponse;
import com.example.employee_registration.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/department")
    public ResponseEntity<List<DepartmentResponse>> getDepartments(){
        List<DepartmentResponse> response = departmentService.getDepartments();
        return ResponseEntity.ok(response);
    }
}

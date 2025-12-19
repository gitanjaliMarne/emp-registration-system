package com.example.employee_registration.service;

import com.example.employee_registration.model.client.DepartmentResponse;
import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> getDepartments();
}

package com.example.employee_registration.service;

import com.example.employee_registration.model.client.EmployeeResponse;
import com.example.employee_registration.model.web.AddEmployeeRequest;
import com.example.employee_registration.model.web.GetAllEmployeeResponse;
import com.example.employee_registration.model.web.UpdateEmployeeRequest;

public interface EmployeeService {
    EmployeeResponse addEmployee(AddEmployeeRequest request);
    EmployeeResponse updateEmployee(UpdateEmployeeRequest request);
    void deleteEmployeeById(Long id);
    GetAllEmployeeResponse getAllEmployees(String search, int pageNumber, int pageSize);
    EmployeeResponse getEmployeeDeatilsById(Long id);
}

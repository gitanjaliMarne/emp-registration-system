package com.example.employee_registration.controller;

import com.example.employee_registration.model.client.EmployeeResponse;
import com.example.employee_registration.model.web.AddEmployeeRequest;
import com.example.employee_registration.model.web.GetAllEmployeeResponse;
import com.example.employee_registration.model.web.UpdateEmployeeRequest;
import com.example.employee_registration.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.employee_registration.utils.ApplicationConstant.DEFAULT_PAGE_NUMBER;
import static com.example.employee_registration.utils.ApplicationConstant.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<Void> addEmployee(
            @Valid @RequestBody AddEmployeeRequest request) {

        EmployeeResponse response = employeeService.addEmployee(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/employees")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @Valid @RequestBody UpdateEmployeeRequest request) {

        EmployeeResponse response = employeeService.updateEmployee(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Employee deleted successfully with id:" + id);
    }

    @GetMapping("/employees")
    public ResponseEntity<GetAllEmployeeResponse> getAllEmployees(@RequestParam(required = false) String search,
                                                                  @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) int pageNumber,
                                                                  @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize){
        GetAllEmployeeResponse response =
                employeeService.getAllEmployees(search, pageNumber, pageSize);
        if (response.getEmployees() == null || response.getEmployees().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeDetailById(@PathVariable Long id){
        EmployeeResponse response = employeeService.getEmployeeDeatilsById(id);
        return ResponseEntity.ok(response);
    }
}

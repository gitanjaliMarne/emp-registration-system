package com.example.employee_registration.service.serviceImpl;

import com.example.employee_registration.entity.Department;
import com.example.employee_registration.entity.Employee;
import com.example.employee_registration.mapper.EmployeeMapper;
import com.example.employee_registration.model.client.EmployeeResponse;
import com.example.employee_registration.model.web.AddEmployeeRequest;
import com.example.employee_registration.model.web.GetAllEmployeeResponse;
import com.example.employee_registration.model.web.UpdateEmployeeRequest;
import com.example.employee_registration.repository.DepartmentRepository;
import com.example.employee_registration.repository.EmployeeRepository;
import com.example.employee_registration.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponse addEmployee(AddEmployeeRequest request) {

        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Employee email already exists");
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() ->
                        new RuntimeException("Department not found with id: "
                                + request.getDepartmentId()));

        Employee employee = employeeMapper.mapAddEmployeeRequestAndDepartmentToEmployeeEntity(request, department);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.mapEmployeeEntityToEmployeeResponse(savedEmployee);
    }

    @Override
    public EmployeeResponse updateEmployee(UpdateEmployeeRequest request) {

        Employee employee = employeeRepository.findById(request.getId())
                .orElseThrow(() ->
                        new RuntimeException("Employee not found with id: "
                                + request.getId()));

        if (employeeRepository.existsByEmailAndIdNot(request.getEmail(), request.getId())) {
            throw new IllegalArgumentException("Employee email already exists");
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() ->
                        new RuntimeException("Department not found with id: "
                                + request.getDepartmentId()));

        employeeMapper.updateEmployeeEntityFromRequestAndDepartment(request, department, employee);

        Employee updated = employeeRepository.save(employee);

        return employeeMapper.mapEmployeeEntityToEmployeeResponse(updated);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: "+ id));

        employeeRepository.delete(employee);
    }

    @Override
    public GetAllEmployeeResponse getAllEmployees(String search, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber , pageSize);

        Page<Employee> employeePage;

        if (search != null && !search.isBlank()) {
            employeePage = employeeRepository
                    .findByNameContainingIgnoreCase(search, pageable);
        } else {
            employeePage = employeeRepository.findAll(pageable);
        }

        List<EmployeeResponse> responses = employeePage.getContent()
                .stream()
                .map(employeeMapper::mapEmployeeEntityToEmployeeResponse)
                .toList();

        GetAllEmployeeResponse response = new GetAllEmployeeResponse();
        response.setEmployees(responses);
        response.setTotalRecords((int) employeePage.getTotalElements());

        return response;
    }

    @Override
    public EmployeeResponse getEmployeeDeatilsById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: "+ id));
        return employeeMapper.mapEmployeeEntityToEmployeeResponse(employee);
    }
}

package com.example.employee_registration.service.serviceImpl;

import com.example.employee_registration.entity.Department;
import com.example.employee_registration.mapper.DepartmentMapper;
import com.example.employee_registration.model.client.DepartmentResponse;
import com.example.employee_registration.repository.DepartmentRepository;
import com.example.employee_registration.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentResponse> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departmentMapper.mapDepartmentResponseToResponseList(departments);
    }
}

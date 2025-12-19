package com.example.employee_registration.mapper;

import com.example.employee_registration.entity.Department;
import com.example.employee_registration.model.client.DepartmentResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentResponse mapDepartmentEntityToDepartmentResponse(Department department);

    List<DepartmentResponse> mapDepartmentResponseToResponseList(List<Department> departments);
}

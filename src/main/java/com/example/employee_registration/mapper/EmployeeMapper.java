package com.example.employee_registration.mapper;

import com.example.employee_registration.entity.Department;
import com.example.employee_registration.entity.Employee;
import com.example.employee_registration.model.client.EmployeeResponse;
import com.example.employee_registration.model.web.AddEmployeeRequest;
import com.example.employee_registration.model.web.UpdateEmployeeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.name", target = "departmentName")
    EmployeeResponse mapEmployeeEntityToEmployeeResponse(Employee employee);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "department", source = "department")
    Employee mapAddEmployeeRequestAndDepartmentToEmployeeEntity(AddEmployeeRequest request, Department department);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "department", source = "department")
    void updateEmployeeEntityFromRequestAndDepartment(UpdateEmployeeRequest request, Department department, @MappingTarget Employee employee);
}

package com.example.employee_registration.mapper;

import com.example.employee_registration.entity.Department;
import com.example.employee_registration.entity.Employee;
import com.example.employee_registration.model.client.EmployeeResponse;
import com.example.employee_registration.model.web.AddEmployeeRequest;
import com.example.employee_registration.model.web.UpdateEmployeeRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-19T15:32:47+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeResponse mapEmployeeEntityToEmployeeResponse(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        Long departmentId = null;
        String departmentName = null;
        Long id = null;
        String name = null;
        String email = null;
        String address = null;
        String role = null;
        double salary = 0.0d;

        departmentId = employeeDepartmentId( employee );
        departmentName = employeeDepartmentName( employee );
        id = employee.getId();
        name = employee.getName();
        email = employee.getEmail();
        address = employee.getAddress();
        role = employee.getRole();
        if ( employee.getSalary() != null ) {
            salary = employee.getSalary();
        }

        EmployeeResponse employeeResponse = new EmployeeResponse( id, name, email, address, role, departmentId, departmentName, salary );

        return employeeResponse;
    }

    @Override
    public Employee mapAddEmployeeRequestAndDepartmentToEmployeeEntity(AddEmployeeRequest request, Department department) {
        if ( request == null && department == null ) {
            return null;
        }

        Employee.EmployeeBuilder employee = Employee.builder();

        if ( request != null ) {
            employee.name( request.getName() );
            employee.address( request.getAddress() );
            employee.email( request.getEmail() );
            employee.salary( request.getSalary() );
            employee.role( request.getRole() );
        }
        employee.department( department );

        return employee.build();
    }

    @Override
    public void updateEmployeeEntityFromRequestAndDepartment(UpdateEmployeeRequest request, Department department, Employee employee) {
        if ( request == null && department == null ) {
            return;
        }

        if ( request != null ) {
            employee.setName( request.getName() );
            employee.setAddress( request.getAddress() );
            employee.setEmail( request.getEmail() );
            employee.setSalary( request.getSalary() );
            employee.setRole( request.getRole() );
        }
        employee.setDepartment( department );
    }

    private Long employeeDepartmentId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Department department = employee.getDepartment();
        if ( department == null ) {
            return null;
        }
        Long id = department.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String employeeDepartmentName(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Department department = employee.getDepartment();
        if ( department == null ) {
            return null;
        }
        String name = department.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

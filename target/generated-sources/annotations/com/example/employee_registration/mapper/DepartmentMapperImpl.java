package com.example.employee_registration.mapper;

import com.example.employee_registration.entity.Department;
import com.example.employee_registration.model.client.DepartmentResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-19T15:32:47+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public DepartmentResponse mapDepartmentEntityToDepartmentResponse(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentResponse departmentResponse = new DepartmentResponse();

        departmentResponse.setId( department.getId() );
        departmentResponse.setName( department.getName() );

        return departmentResponse;
    }

    @Override
    public List<DepartmentResponse> mapDepartmentResponseToResponseList(List<Department> departments) {
        if ( departments == null ) {
            return null;
        }

        List<DepartmentResponse> list = new ArrayList<DepartmentResponse>( departments.size() );
        for ( Department department : departments ) {
            list.add( mapDepartmentEntityToDepartmentResponse( department ) );
        }

        return list;
    }
}

package com.example.employee_registration.model.web;

import com.example.employee_registration.model.client.EmployeeResponse;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
public class GetAllEmployeeResponse {
    private List<EmployeeResponse> employees;
    private int totalRecords;
}

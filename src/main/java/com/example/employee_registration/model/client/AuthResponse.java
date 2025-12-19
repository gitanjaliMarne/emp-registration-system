package com.example.employee_registration.model.client;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private Long userId;
    private String email;
    private String fullName;
}


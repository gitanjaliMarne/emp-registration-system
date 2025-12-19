package com.example.employee_registration.service;

import com.example.employee_registration.model.client.AuthResponse;
import com.example.employee_registration.model.web.LoginRequest;
import com.example.employee_registration.model.web.SignupRequest;

public interface AuthService {

    AuthResponse signup(SignupRequest request);
    AuthResponse login(LoginRequest request);
}

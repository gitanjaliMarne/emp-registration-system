package com.example.employee_registration.service.serviceImpl;

import com.example.employee_registration.entity.UserAccount;
import com.example.employee_registration.mapper.AuthMapper;
import com.example.employee_registration.model.client.AuthResponse;
import com.example.employee_registration.model.web.LoginRequest;
import com.example.employee_registration.model.web.SignupRequest;
import com.example.employee_registration.repository.UserAccountRepository;
import com.example.employee_registration.service.AuthService;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;

    @Override
    public AuthResponse signup(SignupRequest request) {
        validatePasswordMatch(request.getPassword(), request.getConfirmPassword());
        validatePasswordStrength(request.getPassword());

        if (repository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new RuntimeException(
                    "User already exists with email: " + request.getEmail()
            );
        }

        UserAccount user = authMapper.mapSignupRequestToUserAccount(request);
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        UserAccount savedUser = repository.save(user);

        return authMapper.mapUserAccountToAuthResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        if (request.getPassword() == null || request.getPassword().length() < 8) {
            throw new BadCredentialsException("Invalid email or password");
        }

        UserAccount user = repository.findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));


        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        return authMapper.mapUserAccountToAuthResponse(user);
    }

    private void validatePasswordStrength(String rawPassword) {
        boolean hasUpper = rawPassword.chars().anyMatch(Character::isUpperCase);
        boolean hasLower = rawPassword.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = rawPassword.chars().anyMatch(Character::isDigit);
        if (!(hasUpper && hasLower && hasDigit)) {
            throw new ValidationException("Password must include upper, lower, and digit characters");
        }
        if (rawPassword.length() < 8) {
            throw new ValidationException("Password must be at least 8 characters long");
        }
    }

    private void validatePasswordMatch(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new ValidationException("Invalid email or password");
        }
    }
}

package com.example.employee_registration.mapper;

import com.example.employee_registration.entity.UserAccount;
import com.example.employee_registration.model.client.AuthResponse;
import com.example.employee_registration.model.web.SignupRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "email", expression = "java(request.getEmail().toLowerCase())")
    UserAccount mapSignupRequestToUserAccount(SignupRequest request);

    AuthResponse mapUserAccountToAuthResponse(UserAccount user);
}

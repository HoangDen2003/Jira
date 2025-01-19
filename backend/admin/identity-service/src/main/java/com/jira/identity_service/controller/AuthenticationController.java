package com.jira.identity_service.controller;

import java.text.ParseException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jira.identity_service.dto.request.LogoutRequest;
import com.jira.identity_service.dto.request.RefreshRequest;
import com.jira.identity_service.dto.request.UserProfileCreationRequest;
import com.jira.identity_service.dto.request.UserRequest;
import com.jira.identity_service.dto.response.ApiResponse;
import com.jira.identity_service.dto.response.AuthenticationResponse;
import com.jira.identity_service.dto.response.UserResponse;
import com.jira.identity_service.dto.response.UserTokenResponse;
import com.jira.identity_service.service.AuthenticationService;
import com.jira.identity_service.service.UserService;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    UserService userService;
    AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    ApiResponse<UserResponse> creatUser(@RequestBody UserProfileCreationRequest userProfileCreationRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(userProfileCreationRequest))
                .code(200)
                .message("User created successfully")
                .build();
    }

    @PostMapping("/sign-in")
    ApiResponse<UserTokenResponse> signIn(@RequestBody @Valid UserRequest userRequest) {
        return ApiResponse.<UserTokenResponse>builder()
                .result(authenticationService.signIn(userRequest))
                .code(200)
                .message("User logged in successfully")
                .build();
    }

    @PostMapping("/refresh-token")
    ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshRequest refreshRequest)
            throws ParseException, JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.refreshToken(refreshRequest))
                .code(200)
                .message("Token refreshed successfully")
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().build();
    }
}

package com.jira.identity_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.jira.identity_service.dto.request.UserProfileCreationRequest;
import com.jira.identity_service.dto.response.ApiResponse;
import com.jira.identity_service.dto.response.UserResponse;
import com.jira.identity_service.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/user")
@RestController
public class UserController {
    UserService userService;

    @PostMapping("/create")
    ApiResponse<UserResponse> creatUser(@RequestBody UserProfileCreationRequest userProfileCreationRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(userProfileCreationRequest))
                .code(200)
                .message("User created successfully")
                .build();
    }

    @DeleteMapping("/delete/{id}")
    ApiResponse<String> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ApiResponse.<String>builder()
                .result("Delete Account Successful")
                .code(200)
                .build();
    }

    @PutMapping("/edit/{id}")
    ApiResponse<UserResponse> updateUser(
            @PathVariable Integer id, @RequestBody UserProfileCreationRequest userProfileCreationRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(id, userProfileCreationRequest))
                .code(200)
                .build();
    }

    @GetMapping("/all")
    ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUsers())
                .code(200)
                .message("All Users")
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<UserResponse> getUser(@PathVariable Integer id) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(id))
                .code(200)
                .message("An user")
                .build();
    }
}

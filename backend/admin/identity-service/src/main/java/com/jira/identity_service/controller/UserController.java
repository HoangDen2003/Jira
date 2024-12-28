package com.jira.identity_service.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.jira.identity_service.dto.request.UserProfileCreationRequest;
import com.jira.identity_service.dto.request.UserRequest;
import com.jira.identity_service.dto.response.ApiResponse;
import com.jira.identity_service.dto.response.UserResponse;
import com.jira.identity_service.dto.response.UserTokenResponse;
import com.jira.identity_service.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/auth")
@RestController
public class UserController {
    UserService userService;

    /*************  ✨ Codeium Command ⭐  *************/
    /**
     * Creates a new user.
     *
     * @param user the user object from the request body
     * @return an ApiResponse containing the created user and a success message
     */
    /******  10adfd13-a983-4f68-b2c5-72ed1a9d0227  *******/
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
                .result(userService.signIn(userRequest))
                .code(200)
                .message("User logged in successfully")
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
    ApiResponse<UserResponse> updateUser(@PathVariable Integer id, @RequestBody UserProfileCreationRequest userProfileCreationRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(id, userProfileCreationRequest))
                .code(200)
                .build();
    }

    /*************  ✨ Codeium Command ⭐  *************/
    /**
     * Retrieves all users.
     *
     * @return an ApiResponse containing a list of UserResponse objects and a success message
     */
    /******  f482c462-f891-4b98-87b6-f23c1c13850d  *******/
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

package com.jira.permission_service.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.jira.permission_service.dto.request.PermissionRequest;
import com.jira.permission_service.dto.response.ApiResponse;
import com.jira.permission_service.dto.response.PermissionResponse;
import com.jira.permission_service.service.PermissionSchemeService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/permission-scheme")
public class PermissionSchemeController {

    PermissionSchemeService permissionSchemeService;

    @PostMapping("/create")
    ApiResponse<PermissionResponse> createPermissionScheme(@RequestBody @Valid PermissionRequest permissionRequest) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionSchemeService.createPermissionScheme(permissionRequest))
                .code(200)
                .message("Created Permission Successfully")
                .build();
    }

    @PutMapping("/update/{id}")
    ApiResponse<PermissionResponse> updatePermissionScheme(
            @PathVariable Integer id, @RequestBody PermissionRequest permissionRequest) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionSchemeService.updatePermissionScheme(id, permissionRequest))
                .code(200)
                .message("Updated Permission Successfully")
                .build();
    }

    @DeleteMapping("/delete/{id}")
    ApiResponse<String> deletePermissionScheme(@PathVariable Integer id) {
        permissionSchemeService.deletePermissionScheme(id);
        return ApiResponse.<String>builder()
                .result("Deleted Permission Successfully")
                .code(200)
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAllPermissionSchemes() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionSchemeService.getAllPermissionSchemes())
                .code(200)
                .message("All Permissions")
                .build();
    }
}

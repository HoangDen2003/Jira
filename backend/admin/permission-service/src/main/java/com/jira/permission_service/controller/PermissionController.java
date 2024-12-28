package com.jira.permission_service.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jira.permission_service.dto.request.PermissionRequest;
import com.jira.permission_service.dto.response.ApiResponse;
import com.jira.permission_service.dto.response.PermissionResponse;
import com.jira.permission_service.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping()
public class PermissionController {

    PermissionService permissionService;

    @PostMapping("/create")
    ApiResponse<PermissionResponse> createPermission(@RequestBody @Valid PermissionRequest permissionRequest) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.createPermission(permissionRequest))
                .code(200)
                .message("Created Permission Successfully")
                .build();
    }

    @PutMapping("/update/{id}")
    ApiResponse<PermissionResponse> updatePermission(
            @PathVariable Integer id, @RequestBody PermissionRequest permissionRequest) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.updatePermission(id, permissionRequest))
                .code(200)
                .message("Updated Permission Successfully")
                .build();
    }

    @DeleteMapping("/delete/{id}")
    ApiResponse<String> deletePermission(@PathVariable Integer id) {
        permissionService.deletePermission(id);
        return ApiResponse.<String>builder()
                .result("Deleted Permission Successfully")
                .code(200)
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAllPermissions() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAllPermissions())
                .code(200)
                .message("All Permissions")
                .build();
    }
}

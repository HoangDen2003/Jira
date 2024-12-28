package com.jira.permission_service.controller;

import com.jira.permission_service.dto.request.SchemePermissionRequest;
import com.jira.permission_service.dto.response.ApiResponse;
import com.jira.permission_service.dto.response.SchemePermissionResponse;
import com.jira.permission_service.service.SchemePermissionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/scheme-permission")
public class SchemePermissionController {
    
    SchemePermissionService schemePermissionService;

    @PostMapping("/create")
    ApiResponse<SchemePermissionResponse> createSchemePermission(@RequestBody @Valid SchemePermissionRequest schemePermissionRequest) {
        return ApiResponse.<SchemePermissionResponse>builder()
                .result(schemePermissionService.createSchemePermission(schemePermissionRequest))
                .code(200)
                .message("Created Scheme Permission Successfully")
                .build();
    }

    @PutMapping("/update/{id}")
    ApiResponse<SchemePermissionResponse> updateSchemePermission(
            @PathVariable Integer id, @RequestBody SchemePermissionRequest schemePermissionRequest) {
        return ApiResponse.<SchemePermissionResponse>builder()
                .result(schemePermissionService.updateSchemePermission(id, schemePermissionRequest))
                .code(200)
                .message("Updated Scheme Permission Successfully")
                .build();
    }

    @DeleteMapping("/delete/{id}")
    ApiResponse<String> deleteSchemePermission(@PathVariable Integer id) {
        schemePermissionService.deleteSchemePermission(id);
        return ApiResponse.<String>builder()
                .result("Deleted Scheme Permission Successfully")
                .code(200)
                .build();
    }

    @GetMapping
    ApiResponse<List<SchemePermissionResponse>> getAllSchemePermissions() {
        return ApiResponse.<List<SchemePermissionResponse>>builder()
                .result(schemePermissionService.getAllSchemePermissions())
                .code(200)
                .message("All Scheme Permissions")
                .build();
    }
}

package com.jira.permission_service.controller;

import com.jira.permission_service.dto.request.PermissionAttributeRequest;
import com.jira.permission_service.dto.request.PermissionRequest;
import com.jira.permission_service.dto.response.ApiResponse;
import com.jira.permission_service.dto.response.PermissionAttributeResponse;
import com.jira.permission_service.dto.response.PermissionResponse;
import com.jira.permission_service.service.PermissionAttributeService;
import com.jira.permission_service.service.PermissionSchemeService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/permission-attribute")
public class PermissionAttributeController {

    PermissionAttributeService permissionAttributeService;

    @PostMapping("/create")
    ApiResponse<PermissionAttributeResponse> createPermissionScheme(@RequestBody @Valid PermissionAttributeRequest permissionAttributeRequest) {
        return ApiResponse.<PermissionAttributeResponse>builder()
                .result(permissionAttributeService.createPermissionAttribute(permissionAttributeRequest))
                .code(200)
                .message("Created Permission Successfully")
                .build();
    }

    @PutMapping("/update/{id}")
    ApiResponse<PermissionAttributeResponse> updatePermissionScheme(
            @PathVariable Integer id, @RequestBody PermissionAttributeRequest permissionAttributeRequest) {
        return ApiResponse.<PermissionAttributeResponse>builder()
                .result(permissionAttributeService.updatePermissionAttribute(id, permissionAttributeRequest))
                .code(200)
                .message("Updated Permission Successfully")
                .build();
    }

    @DeleteMapping("/delete/{id}")
    ApiResponse<String> deletePermissionScheme(@PathVariable Integer id) {
        permissionAttributeService.deletePermissionAttribute(id);
        return ApiResponse.<String>builder()
                .result("Deleted Permission Successfully")
                .code(200)
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionAttributeResponse>> getAllPermissionSchemes() {
        return ApiResponse.<List<PermissionAttributeResponse>>builder()
                .result(permissionAttributeService.getAllPermissionAttributes())
                .code(200)
                .message("All Permissions")
                .build();
    }
}

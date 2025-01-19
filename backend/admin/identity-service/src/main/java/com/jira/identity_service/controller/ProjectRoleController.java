package com.jira.identity_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.jira.identity_service.dto.request.ProjectRoleRequest;
import com.jira.identity_service.dto.response.ApiResponse;
import com.jira.identity_service.dto.response.RoleResponse;
import com.jira.identity_service.service.ProjectRoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/roles/projects/admin")
@RestController
public class ProjectRoleController {

    ProjectRoleService projectRoleService;

    @PostMapping("/create")
    ApiResponse<RoleResponse> createByAdmin(@RequestBody ProjectRoleRequest projectRoleRequest) {
        return ApiResponse.<RoleResponse>builder()
                .result(projectRoleService.createByAdmin(projectRoleRequest))
                .code(200)
                .message("Created Project Role Successfully By Admin")
                .build();
    }

    // edit roles
    @PutMapping("/update/{id}")
    ApiResponse<RoleResponse> updateRole(@RequestBody ProjectRoleRequest projectRoleRequest, @PathVariable Integer id) {
        return ApiResponse.<RoleResponse>builder()
                .result(projectRoleService.updateProjectRoleById(id, projectRoleRequest))
                .message("Update successfully")
                .code(200)
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<RoleResponse> getProjectRole(@PathVariable Integer id) {
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .result(projectRoleService.getProjectRoleById(id))
                .message("Get A Default Project Role")
                .build();
    }

    @GetMapping()
    ApiResponse<List<RoleResponse>> getAllProjectRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .code(200)
                .result(projectRoleService.getAllProjectRoles())
                .message("Get All Default Project Roles By Admin")
                .build();
    }
}

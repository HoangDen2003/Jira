package com.jira.identity_service.controller;

import com.jira.identity_service.dto.response.ApiResponse;
import com.jira.identity_service.dto.response.RoleResponse;
import com.jira.identity_service.service.GlobalRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/roles/globals")
@RestController
public class GlobalRoleController {

    GlobalRoleService globalRoleService;

    @GetMapping
    ApiResponse<List<RoleResponse>> getRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(globalRoleService.getGlobalRoles())
                .code(200)
                .message("All Global Roles")
                .build();
    }

}

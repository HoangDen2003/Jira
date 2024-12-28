package com.jira.project_service.controller;

import com.jira.project_service.dto.request.ProjectTypeRequest;
import com.jira.project_service.dto.response.ApiResponse;
import com.jira.project_service.dto.response.ProjectResponse;
import com.jira.project_service.dto.response.ProjectTypeResponse;
import com.jira.project_service.entity.ProjectType;
import com.jira.project_service.service.ProjectTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/type")
public class ProjectTypeController {
    ProjectTypeService projectTypeService;

    @PostMapping("/create")
    ApiResponse<ProjectTypeResponse> create(@RequestBody ProjectTypeRequest projectTypeRequest) {
        return ApiResponse.<ProjectTypeResponse>builder()
                .result(projectTypeService.createProjectType(projectTypeRequest))
                .code(200)
                .message("Created a project type successfully")
                .build();
    }

}

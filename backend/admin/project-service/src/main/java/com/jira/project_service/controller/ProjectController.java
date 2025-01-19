package com.jira.project_service.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.jira.project_service.dto.request.ProjectRequest;
import com.jira.project_service.dto.request.ProjectUserRequest;
import com.jira.project_service.dto.response.*;
import com.jira.project_service.service.ProjectService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping()
public class ProjectController {

    ProjectService projectService;

    @PostMapping("/create")
    ApiResponse<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest projectRequest) {
        return ApiResponse.<ProjectResponse>builder()
                .result(projectService.createProject(projectRequest))
                .code(200)
                .message("Created Project Successfully")
                .build();
    }

    // ADDING MEMBERS
    @PostMapping("/add/user")
    ApiResponse<UserResponse> addUserToProject(@RequestBody @Valid ProjectUserRequest projectUserRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(projectService.addUserToProject(projectUserRequest))
                .message("Add member successfully")
                .code(200)
                .build();
    }

    @PutMapping("/update/{id}")
    ApiResponse<ProjectResponse> updateProject(@PathVariable Integer id, @RequestBody ProjectRequest projectRequest) {
        return ApiResponse.<ProjectResponse>builder()
                .result(projectService.updateProject(id, projectRequest))
                .code(200)
                .message("Updated Project Successfully")
                .build();
    }

    // update adding member (change project role)
    @PutMapping("/roles/update/{id}")
    ApiResponse<UserResponse> updateProject(
            @PathVariable Integer id, @RequestBody ProjectUserRequest projectUserRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(projectService.updateProjectRole(id, projectUserRequest))
                .code(200)
                .message("Updated Project Successfully")
                .build();
    }

    @DeleteMapping("/delete/{id}")
    ApiResponse<String> deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
        return ApiResponse.<String>builder()
                .result("Deleted Project Successfully")
                .code(200)
                .build();
    }

    // todo: delete a user from a project
    @DeleteMapping("/delete/user/{id}")
    ApiResponse<String> deleteUserFromProject(@PathVariable Integer id) {
        projectService.deleteUserFromProject(id);
        return ApiResponse.<String>builder()
                .result("Delete User From Project Successfully")
                .code(200)
                .build();
    }

    @GetMapping("/all")
    ApiResponse<List<ProjectResponse>> getAllProjects() {
        return ApiResponse.<List<ProjectResponse>>builder()
                .result(projectService.getAllProjects())
                .message("All Projects")
                .build();
    }

    // todo: GET AN ISSUE FROM PROJECT
    @GetMapping("/{projectId}/boards")
    ApiResponse<List<IssueResponse>> getIssueByIdProjectId(
            @PathVariable Integer projectId,
            @RequestParam(value = "selectedIssue", required = false) Integer issueId,
            @RequestParam(value = "text", required = false) String text) {
        if (issueId != null) {
            return ApiResponse.<List<IssueResponse>>builder()
                    .result(projectService.getIssueById(projectId, issueId))
                    .message("Get An Issue By ProjectId And IssueId")
                    .code(200)
                    .build();
        }
        if (text != null && !text.isEmpty()) {
            return ApiResponse.<List<IssueResponse>>builder()
                    .result(projectService.getIssuesByProjectId(projectId, text))
                    .message("Get Issues By Text = " + text)
                    .code(200)
                    .build();
        }
        return ApiResponse.<List<IssueResponse>>builder()
                .result(projectService.getIssuesByProjectId(projectId, ""))
                .message("Get All Issues")
                .code(200)
                .build();
    }
}

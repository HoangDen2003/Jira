package com.jira.issue_service.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.jira.issue_service.dto.request.IssueRequest;
import com.jira.issue_service.dto.response.ApiResponse;
import com.jira.issue_service.dto.response.IssueResponse;
import com.jira.issue_service.service.IssueService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping()
public class IssueController {
    IssueService issueService;

    @PostMapping("/create")
    ApiResponse<IssueResponse> createIssue(@RequestBody @Valid IssueRequest issueRequest) {
        return ApiResponse.<IssueResponse>builder()
                .result(issueService.createIssue(issueRequest))
                .code(200)
                .message("Created a Issue successfully")
                .build();
    }

    @PutMapping("/update/{id}")
    ApiResponse<IssueResponse> updateIssue(@PathVariable Integer id, @RequestBody @Valid IssueRequest issueRequest) {
        return ApiResponse.<IssueResponse>builder()
                .result(issueService.updateIssue(id, issueRequest))
                .code(200)
                .message("Updated a Issue successfully")
                .build();
    }

    @DeleteMapping("/delete/{id}")
    ApiResponse<String> deleteIssue(@PathVariable Integer id) {
        issueService.deleteIssue(id);
        return ApiResponse.<String>builder()
                .result("Deleted a Issue successfully")
                .code(200)
                .build();
    }

    @GetMapping("/all-issue-project/{projectId}")
    ApiResponse<List<IssueResponse>> getIssueByProjectId(
            @PathVariable Integer projectId, @RequestParam(value = "text", required = false) String text) {
        return ApiResponse.<List<IssueResponse>>builder()
                .result(issueService.getIssuesByProjectId(projectId, text))
                .message("All issues")
                .code(200)
                .build();
    }

    @GetMapping("/{id}/{projectId}")
    ApiResponse<IssueResponse> getIssueById(@PathVariable Integer id, @PathVariable Integer projectId) {
        return ApiResponse.<IssueResponse>builder()
                .result(issueService.getIssueById(id, projectId))
                .code(200)
                .message("Get an issue")
                .build();
    }

    @GetMapping()
    ApiResponse<List<IssueResponse>> getAllIssues() {
        return ApiResponse.<List<IssueResponse>>builder()
                .result(issueService.getAllIssues())
                .code(200)
                .message("All Issues")
                .build();
    }
}

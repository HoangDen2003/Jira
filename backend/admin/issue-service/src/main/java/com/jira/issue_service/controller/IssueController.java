package com.jira.issue_service.controller;

import com.jira.issue_service.service.IssueService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.jira.issue_service.dto.request.IssueRequest;
import com.jira.issue_service.dto.response.ApiResponse;
import com.jira.issue_service.dto.response.IssueResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

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

    @PostMapping("/all-issue-project")
    ApiResponse<List<IssueResponse>> getIssueByProjectId(@RequestBody Integer projectId) {
        return ApiResponse.<List<IssueResponse>>builder()
                .result(issueService.getIssuesByProjectId(projectId))
                .message("All issues")
                .code(200)
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

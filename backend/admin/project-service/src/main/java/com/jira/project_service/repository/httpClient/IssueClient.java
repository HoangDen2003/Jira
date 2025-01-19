package com.jira.project_service.repository.httpClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.jira.project_service.dto.response.ApiResponse;
import com.jira.project_service.dto.response.IssueResponse;

@FeignClient(name = "issue-service", url = "http://localhost:8083/issue")
public interface IssueClient {

    @GetMapping("/all-issue-project/{projectId}")
    ApiResponse<List<IssueResponse>> getIssueByProjectId(
            @PathVariable Integer projectId, @RequestParam(value = "text", required = false) String text);

    @GetMapping("/{id}/{projectId}")
    ApiResponse<IssueResponse> getIssueByProjectIdIssueId(@PathVariable Integer projectId, @PathVariable Integer id);
}

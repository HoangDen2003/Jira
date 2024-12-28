package com.jira.project_service.repository.httpClient;

import com.jira.project_service.dto.response.ApiResponse;
import com.jira.project_service.dto.response.IssueResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "issue-service", url = "http://localhost:8083/api/v1/issue")
public interface IssueClient {

    @PostMapping("/all-issue-project")
    ApiResponse<List<IssueResponse>> getIssueByProjectId(@RequestBody Integer projectId);

}

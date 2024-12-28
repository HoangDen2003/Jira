package com.jira.project_service.repository.httpClient;

import com.jira.project_service.dto.response.ApiResponse;
import com.jira.project_service.dto.response.RoleResponse;
import com.jira.project_service.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "identity-service", url = "http://localhost:8080/api/v1/identity")  // URL của identity-service
public interface UserClient {

    @GetMapping("/auth/all")
    ApiResponse<List<UserResponse>> getAllUsers();

    @GetMapping("/auth/{id}")
    ApiResponse<UserResponse> getUserById(@PathVariable Integer id);

    @GetMapping("/roles/projects/admin/{id}")
    ApiResponse<RoleResponse> getProjectRole(@PathVariable Integer id);

}

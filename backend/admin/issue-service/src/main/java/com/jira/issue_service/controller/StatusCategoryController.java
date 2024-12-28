package com.jira.issue_service.controller;

import com.jira.issue_service.dto.request.StatusCategoryRequest;
import com.jira.issue_service.dto.response.ApiResponse;
import com.jira.issue_service.dto.response.StatusCategoryResponse;
import com.jira.issue_service.service.StatusCategoryService;
import jakarta.validation.Valid;
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
@RequestMapping("/category")
public class StatusCategoryController {
    StatusCategoryService statusCategoryService;

    @PostMapping("/create")
    ApiResponse<StatusCategoryResponse> createCategory(@RequestBody @Valid StatusCategoryRequest statusCategoryRequest) {
        return ApiResponse.<StatusCategoryResponse>builder()
                .result(statusCategoryService.createStatusCategory(statusCategoryRequest))
                .code(200)
                .message("Created a status category successfully")
                .build();
    }

}

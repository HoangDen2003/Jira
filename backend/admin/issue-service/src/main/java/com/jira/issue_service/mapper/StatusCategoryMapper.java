package com.jira.issue_service.mapper;

import org.mapstruct.Mapper;

import com.jira.issue_service.dto.request.StatusCategoryRequest;
import com.jira.issue_service.dto.response.StatusCategoryResponse;
import com.jira.issue_service.entity.StatusCategory;

@Mapper(componentModel = "spring")
public interface StatusCategoryMapper {
    StatusCategory toStatusCategory(StatusCategoryRequest statusCategoryRequest);

    StatusCategoryResponse toStatusCategoryResponse(StatusCategory statusCategory);
}

package com.jira.issue_service.service;

import org.springframework.stereotype.Service;

import com.jira.issue_service.dto.request.StatusCategoryRequest;
import com.jira.issue_service.dto.response.StatusCategoryResponse;
import com.jira.issue_service.entity.StatusCategory;
import com.jira.issue_service.mapper.StatusCategoryMapper;
import com.jira.issue_service.repository.StatusCategoryRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@Slf4j
public class StatusCategoryService {

    StatusCategoryRepository statusCategoryRepository;
    StatusCategoryMapper statusCategoryMapper;

    public StatusCategoryResponse createStatusCategory(StatusCategoryRequest statusCategoryRequest) {
        StatusCategory statusCategory = statusCategoryMapper.toStatusCategory(statusCategoryRequest);
        statusCategory = statusCategoryRepository.save(statusCategory);
        return statusCategoryMapper.toStatusCategoryResponse(statusCategory);
    }
}

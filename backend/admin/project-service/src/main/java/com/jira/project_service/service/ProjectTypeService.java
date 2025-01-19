package com.jira.project_service.service;

import org.springframework.stereotype.Service;

import com.jira.project_service.dto.request.ProjectTypeRequest;
import com.jira.project_service.dto.response.ProjectTypeResponse;
import com.jira.project_service.entity.ProjectType;
import com.jira.project_service.mapper.ProjectTypeMapper;
import com.jira.project_service.repository.ProjectTypeRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@Slf4j
public class ProjectTypeService {

    ProjectTypeRepository projectTypeRepository;
    ProjectTypeMapper projectTypeMapper;

    public ProjectTypeResponse createProjectType(ProjectTypeRequest projectTypeRequest) {
        ProjectType projectType = projectTypeMapper.toProjectType(projectTypeRequest);
        projectType = projectTypeRepository.save(projectType);
        return projectTypeMapper.toProjectTypeResponse(projectType);
    }
}

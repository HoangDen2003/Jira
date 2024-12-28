package com.jira.project_service.mapper;

import com.jira.project_service.dto.request.ProjectTypeRequest;
import com.jira.project_service.dto.response.ProjectTypeResponse;
import com.jira.project_service.entity.ProjectType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectTypeMapper {
    ProjectType toProjectType(ProjectTypeRequest projectTypeRequest);
    ProjectTypeResponse toProjectTypeResponse(ProjectType projectType);
}

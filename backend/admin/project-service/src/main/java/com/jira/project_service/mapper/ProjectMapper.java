package com.jira.project_service.mapper;

import java.util.Set;

import com.jira.project_service.dto.response.ProjectResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jira.project_service.dto.request.ProjectRequest;
import com.jira.project_service.dto.response.IssueResponse;
import com.jira.project_service.dto.response.ProjectIssueResponse;
import com.jira.project_service.entity.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project toProject(ProjectRequest projectRequest);

    ProjectResponse toProjectResponse(Project project);

    @Mapping(source = "project", target = "project")
    @Mapping(source = "issues", target = "issues")
    ProjectIssueResponse toProjectIssueResponse(Project project, Set<IssueResponse> issues);
}

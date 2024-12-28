package com.jira.project_service.mapper;

import com.jira.project_service.dto.response.IssueResponse;
import com.jira.project_service.dto.response.ProjectIssueResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import com.jira.project_service.dto.request.ProjectRequest;
import com.jira.project_service.dto.response.ProjectResponse;
import com.jira.project_service.entity.Project;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project toProject(ProjectRequest projectRequest);

    ProjectResponse toProjectResponse(Project project);

    @Mapping(source = "project", target = "project")
    @Mapping(source = "issues", target = "issues")
    ProjectIssueResponse toProjectIssueResponse(Project project, Set<IssueResponse> issues);

}

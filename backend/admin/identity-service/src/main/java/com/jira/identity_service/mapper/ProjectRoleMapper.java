package com.jira.identity_service.mapper;

import org.mapstruct.Mapper;

import com.jira.identity_service.dto.request.ProjectRoleRequest;
import com.jira.identity_service.dto.response.RoleResponse;
import com.jira.identity_service.entity.ProjectRole;

@Mapper(componentModel = "spring")
public interface ProjectRoleMapper {
    ProjectRole toProjectRole(ProjectRoleRequest projectRoleRequest);

    RoleResponse toRoleResponse(ProjectRole projectRole);
}

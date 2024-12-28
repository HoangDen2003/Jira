package com.jira.identity_service.mapper;

import com.jira.identity_service.dto.request.ProjectRoleRequest;
import com.jira.identity_service.dto.response.RoleResponse;
import com.jira.identity_service.entity.ProjectRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectRoleMapper {
    ProjectRole toProjectRole(ProjectRoleRequest projectRoleRequest);
    RoleResponse toRoleResponse(ProjectRole projectRole);
}

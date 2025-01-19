package com.jira.identity_service.mapper;

import org.mapstruct.Mapper;

import com.jira.identity_service.dto.request.GlobalRoleRequest;
import com.jira.identity_service.dto.response.RoleResponse;
import com.jira.identity_service.entity.GlobalRole;

@Mapper(componentModel = "spring")
public interface GlobalRoleMapper {
    GlobalRole toGlobalRole(GlobalRoleRequest globalRoleRequest);

    RoleResponse toRoleResponse(GlobalRole globalRole);
}

package com.jira.permission_service.mapper;

import org.mapstruct.Mapper;

import com.jira.permission_service.dto.request.PermissionRequest;
import com.jira.permission_service.dto.response.PermissionResponse;
import com.jira.permission_service.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);

    PermissionResponse toPermissionResponse(Permission permission);
}

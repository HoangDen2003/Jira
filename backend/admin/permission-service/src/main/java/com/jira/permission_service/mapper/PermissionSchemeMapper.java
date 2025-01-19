package com.jira.permission_service.mapper;

import org.mapstruct.Mapper;

import com.jira.permission_service.dto.request.PermissionRequest;
import com.jira.permission_service.dto.response.PermissionResponse;
import com.jira.permission_service.entity.PermissionScheme;

@Mapper(componentModel = "spring")
public interface PermissionSchemeMapper {
    PermissionScheme toPermissionScheme(PermissionRequest permissionRequest);

    PermissionResponse toPermissionResponse(PermissionScheme permissionScheme);
}

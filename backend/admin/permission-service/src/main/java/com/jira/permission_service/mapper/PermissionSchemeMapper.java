package com.jira.permission_service.mapper;

import com.jira.permission_service.dto.request.PermissionRequest;
import com.jira.permission_service.dto.response.PermissionResponse;
import com.jira.permission_service.entity.PermissionScheme;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionSchemeMapper {
    PermissionScheme toPermissionScheme(PermissionRequest permissionRequest);

    PermissionResponse toPermissionResponse(PermissionScheme permissionScheme);
}

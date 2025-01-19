package com.jira.permission_service.mapper;

import org.mapstruct.Mapper;

import com.jira.permission_service.dto.request.SchemePermissionRequest;
import com.jira.permission_service.dto.response.SchemePermissionResponse;
import com.jira.permission_service.entity.SchemePermission;

@Mapper(componentModel = "spring")
public interface SchemePermissionMapper {
    SchemePermission toSchemePermission(SchemePermissionRequest schemePermissionRequest);

    SchemePermissionResponse toSchemePermissionResponse(SchemePermission schemePermission);
}

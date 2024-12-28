package com.jira.permission_service.mapper;

import com.jira.permission_service.dto.request.PermissionRequest;
import com.jira.permission_service.dto.request.SchemePermissionRequest;
import com.jira.permission_service.dto.response.PermissionResponse;
import com.jira.permission_service.dto.response.SchemePermissionResponse;
import com.jira.permission_service.entity.Permission;
import com.jira.permission_service.entity.SchemePermission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchemePermissionMapper {
    SchemePermission toSchemePermission(SchemePermissionRequest schemePermissionRequest);
    SchemePermissionResponse toSchemePermissionResponse(SchemePermission schemePermission);
}

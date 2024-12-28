package com.jira.permission_service.mapper;

import com.jira.permission_service.dto.request.PermissionAttributeRequest;
import com.jira.permission_service.dto.request.PermissionRequest;
import com.jira.permission_service.dto.response.PermissionAttributeResponse;
import com.jira.permission_service.dto.response.PermissionResponse;
import com.jira.permission_service.entity.PermissionAttribute;
import com.jira.permission_service.entity.PermissionScheme;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionAttributeMapper {
    PermissionAttribute toPermissionAttribute(PermissionAttributeRequest permissionAttributeRequest);

    PermissionAttributeResponse toPermissionAttributeResponse(PermissionAttribute permissionAttribute);
}

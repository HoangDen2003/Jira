package com.jira.permission_service.mapper;

import org.mapstruct.Mapper;

import com.jira.permission_service.dto.request.PermissionAttributeRequest;
import com.jira.permission_service.dto.response.PermissionAttributeResponse;
import com.jira.permission_service.entity.PermissionAttribute;

@Mapper(componentModel = "spring")
public interface PermissionAttributeMapper {
    PermissionAttribute toPermissionAttribute(PermissionAttributeRequest permissionAttributeRequest);

    PermissionAttributeResponse toPermissionAttributeResponse(PermissionAttribute permissionAttribute);
}

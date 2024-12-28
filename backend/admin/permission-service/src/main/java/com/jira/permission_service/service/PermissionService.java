package com.jira.permission_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jira.permission_service.dto.request.PermissionRequest;
import com.jira.permission_service.dto.response.PermissionResponse;
import com.jira.permission_service.entity.Permission;
import com.jira.permission_service.exception.AppException;
import com.jira.permission_service.exception.ErrorCode;
import com.jira.permission_service.mapper.PermissionMapper;
import com.jira.permission_service.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@Slf4j
public class PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse createPermission(PermissionRequest projectRequest) {
        Permission permission = permissionMapper.toPermission(projectRequest);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public PermissionResponse updatePermission(Integer Id, PermissionRequest projectRequest) {
        Permission permission = permissionRepository
                .findById(Id)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
        permission.setName(
                permission.getName().equals(projectRequest.getName())
                        ? permission.getName()
                        : projectRequest.getName());
        permission.setDescription(
                permission.getDescription().equals(projectRequest.getDescription())
                        ? permission.getDescription()
                        : projectRequest.getDescription());
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAllPermissions() {
        var projects = permissionRepository.findAll().stream().toList();
        return projects.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void deletePermission(Integer pId) {
        Permission permission =
                permissionRepository.findById(pId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        permissionRepository.delete(permission);
    }
}

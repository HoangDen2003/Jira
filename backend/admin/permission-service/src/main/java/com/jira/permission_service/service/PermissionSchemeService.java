package com.jira.permission_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jira.permission_service.dto.request.PermissionRequest;
import com.jira.permission_service.dto.response.PermissionResponse;
import com.jira.permission_service.entity.PermissionScheme;
import com.jira.permission_service.exception.AppException;
import com.jira.permission_service.exception.ErrorCode;
import com.jira.permission_service.mapper.PermissionSchemeMapper;
import com.jira.permission_service.repository.PermissionSchemeRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@Slf4j
public class PermissionSchemeService {

    PermissionSchemeRepository permissionSchemeRepository;
    PermissionSchemeMapper permissionSchemeMapper;

    public PermissionResponse createPermissionScheme(PermissionRequest projectRequest) {
        PermissionScheme permissionScheme = permissionSchemeMapper.toPermissionScheme(projectRequest);
        permissionScheme = permissionSchemeRepository.save(permissionScheme);
        return permissionSchemeMapper.toPermissionResponse(permissionScheme);
    }

    public PermissionResponse updatePermissionScheme(Integer Id, PermissionRequest projectRequest) {
        PermissionScheme permissionScheme = permissionSchemeRepository
                .findById(Id)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
        permissionScheme.setName(
                permissionScheme.getName().equals(projectRequest.getName())
                        ? permissionScheme.getName()
                        : projectRequest.getName());
        permissionScheme.setDescription(
                permissionScheme.getDescription().equals(projectRequest.getDescription())
                        ? permissionScheme.getDescription()
                        : projectRequest.getDescription());
        permissionScheme = permissionSchemeRepository.save(permissionScheme);
        return permissionSchemeMapper.toPermissionResponse(permissionScheme);
    }

    public List<PermissionResponse> getAllPermissionSchemes() {
        var permissionSchemes = permissionSchemeRepository.findAll().stream().toList();
        return permissionSchemes.stream()
                .map(permissionSchemeMapper::toPermissionResponse)
                .toList();
    }

    public void deletePermissionScheme(Integer id) {
        PermissionScheme permissionScheme =
                permissionSchemeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        permissionSchemeRepository.delete(permissionScheme);
    }
}

package com.jira.permission_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jira.permission_service.dto.request.SchemePermissionRequest;
import com.jira.permission_service.dto.response.SchemePermissionResponse;
import com.jira.permission_service.entity.SchemePermission;
import com.jira.permission_service.exception.AppException;
import com.jira.permission_service.exception.ErrorCode;
import com.jira.permission_service.mapper.SchemePermissionMapper;
import com.jira.permission_service.repository.SchemePermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@Slf4j
public class SchemePermissionService {

    SchemePermissionRepository schemePermissionRepository;
    SchemePermissionMapper schemePermissionMapper;

    public SchemePermissionResponse createSchemePermission(SchemePermissionRequest schemePermissionRequest) {
        SchemePermission schemePermission = schemePermissionMapper.toSchemePermission(schemePermissionRequest);
        schemePermission = schemePermissionRepository.save(schemePermission);
        return schemePermissionMapper.toSchemePermissionResponse(schemePermission);
    }

    public SchemePermissionResponse updateSchemePermission(
            Integer Id, SchemePermissionRequest schemePermissionRequest) {
        SchemePermission schemePermission = schemePermissionRepository
                .findById(Id)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
        schemePermission.setPermissionScope(
                schemePermission.getPermissionScope().equals(schemePermissionRequest.getPermissionScope())
                        ? schemePermission.getPermissionScope()
                        : schemePermissionRequest.getPermissionScope());
        schemePermission.setPermissionParameter(
                schemePermission.getPermissionParameter().equals(schemePermissionRequest.getPermissionParameter())
                        ? schemePermission.getPermissionParameter()
                        : schemePermissionRequest.getPermissionParameter());
        schemePermission.setPermissionKey(
                schemePermission.getPermissionKey().equals(schemePermissionRequest.getPermissionKey())
                        ? schemePermission.getPermissionKey()
                        : schemePermissionRequest.getPermissionKey());
        schemePermission = schemePermissionRepository.save(schemePermission);
        return schemePermissionMapper.toSchemePermissionResponse(schemePermission);
    }

    public List<SchemePermissionResponse> getAllSchemePermissions() {
        var schemePermissions = schemePermissionRepository.findAll().stream().toList();
        return schemePermissions.stream()
                .map(schemePermissionMapper::toSchemePermissionResponse)
                .toList();
    }

    public void deleteSchemePermission(Integer id) {
        SchemePermission schemePermission =
                schemePermissionRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        schemePermissionRepository.delete(schemePermission);
    }
}

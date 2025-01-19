package com.jira.permission_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jira.permission_service.dto.request.PermissionAttributeRequest;
import com.jira.permission_service.dto.response.PermissionAttributeResponse;
import com.jira.permission_service.entity.PermissionAttribute;
import com.jira.permission_service.exception.AppException;
import com.jira.permission_service.exception.ErrorCode;
import com.jira.permission_service.mapper.PermissionAttributeMapper;
import com.jira.permission_service.repository.PermissionAttributeRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@Slf4j
public class PermissionAttributeService {

    PermissionAttributeRepository permissionAttributeRepository;
    PermissionAttributeMapper permissionAttributeMapper;

    public PermissionAttributeResponse createPermissionAttribute(
            PermissionAttributeRequest permissionAttributeRequest) {
        PermissionAttribute permissionAttribute =
                permissionAttributeMapper.toPermissionAttribute(permissionAttributeRequest);
        permissionAttribute = permissionAttributeRepository.save(permissionAttribute);
        return permissionAttributeMapper.toPermissionAttributeResponse(permissionAttribute);
    }

    public PermissionAttributeResponse updatePermissionAttribute(
            Integer Id, PermissionAttributeRequest permissionAttributeRequest) {
        PermissionAttribute permissionAttribute = permissionAttributeRepository
                .findById(Id)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));

        permissionAttribute.setAttributeKey(
                permissionAttribute.getAttributeKey().equals(permissionAttributeRequest.getAttributeKey())
                        ? permissionAttribute.getAttributeKey()
                        : permissionAttributeRequest.getAttributeKey());
        permissionAttribute.setAttributeValue(
                permissionAttribute.getAttributeValue().equals(permissionAttributeRequest.getAttributeValue())
                        ? permissionAttribute.getAttributeValue()
                        : permissionAttributeRequest.getAttributeValue());

        permissionAttribute = permissionAttributeRepository.save(permissionAttribute);
        return permissionAttributeMapper.toPermissionAttributeResponse(permissionAttribute);
    }

    public List<PermissionAttributeResponse> getAllPermissionAttributes() {
        var permissionAttribute =
                permissionAttributeRepository.findAll().stream().toList();
        return permissionAttribute.stream()
                .map(permissionAttributeMapper::toPermissionAttributeResponse)
                .toList();
    }

    public void deletePermissionAttribute(Integer id) {
        PermissionAttribute permissionAttribute = permissionAttributeRepository
                .findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        permissionAttributeRepository.delete(permissionAttribute);
    }
}

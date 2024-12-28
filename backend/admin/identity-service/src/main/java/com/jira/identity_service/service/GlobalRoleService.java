package com.jira.identity_service.service;

import com.jira.identity_service.dto.response.RoleResponse;
import com.jira.identity_service.mapper.GlobalRoleMapper;
import com.jira.identity_service.repository.GlobalRoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@Slf4j
public class GlobalRoleService {

    GlobalRoleRepository globalRoleRepository;
    GlobalRoleMapper globalRoleMapper;

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    public List<RoleResponse> getGlobalRoles() {
        var globalRoles = globalRoleRepository.findAll().stream().toList();
        return globalRoles.stream().map(globalRoleMapper::toRoleResponse).toList();
    }

}

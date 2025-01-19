package com.jira.identity_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.jira.identity_service.dto.request.ProjectRoleRequest;
import com.jira.identity_service.dto.response.RoleResponse;
import com.jira.identity_service.entity.ProjectRole;
import com.jira.identity_service.mapper.ProjectRoleMapper;
import com.jira.identity_service.repository.ProjectRoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@Slf4j
public class ProjectRoleService {

    ProjectRoleRepository projectRoleRepository;
    ProjectRoleMapper projectRoleMapper;

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    public RoleResponse createByAdmin(ProjectRoleRequest projectRoleRequest) {
        ProjectRole projectRole = projectRoleMapper.toProjectRole(projectRoleRequest);
        projectRole = projectRoleRepository.save(projectRole);
        return projectRoleMapper.toRoleResponse(projectRole);
    }

    public List<RoleResponse> getAllProjectRoles() {
        var projectRoles = projectRoleRepository.findAll().stream().toList();
        return projectRoles.stream().map(projectRoleMapper::toRoleResponse).collect(Collectors.toList());
    }

    public RoleResponse getProjectRoleById(Integer id) {
        ProjectRole projectRole = projectRoleRepository.findById(id).orElse(null);
        return projectRoleMapper.toRoleResponse(projectRole);
    }

    public RoleResponse updateProjectRoleById(Integer id, ProjectRoleRequest projectRoleRequest) {
        ProjectRole projectRole = projectRoleRepository.findById(id).orElse(null);
        projectRole.setName(
                projectRoleRequest.getName().equals(projectRole.getName())
                        ? projectRole.getName()
                        : projectRoleRequest.getName());
        projectRole = projectRoleRepository.save(projectRole);
        return projectRoleMapper.toRoleResponse(projectRole);
    }
}

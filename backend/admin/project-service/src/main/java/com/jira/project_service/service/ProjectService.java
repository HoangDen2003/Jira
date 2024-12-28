package com.jira.project_service.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jira.project_service.dto.request.ProjectUserRequest;
import com.jira.project_service.dto.response.*;
import com.jira.project_service.entity.ProjectUser;
import com.jira.project_service.repository.ProjectUserRepository;
import com.jira.project_service.repository.httpClient.IssueClient;
import com.jira.project_service.repository.httpClient.UserClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.jira.project_service.dto.request.ProjectRequest;
import com.jira.project_service.entity.Project;
import com.jira.project_service.exception.AppException;
import com.jira.project_service.exception.ErrorCode;
import com.jira.project_service.mapper.ProjectMapper;
import com.jira.project_service.repository.ProjectRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@Slf4j
public class ProjectService {

    UserClient userClient;
    IssueClient issueClient;
    ProjectRepository projectRepository;
    ProjectUserRepository projectUserRepository;

    ProjectMapper projectMapper;

    public ProjectResponse createProject(ProjectRequest projectRequest) {
        Project project = projectMapper.toProject(projectRequest);
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    public ProjectResponse updateProject(Integer Id, ProjectRequest projectRequest) {
        Project project =
                projectRepository.findById(Id).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
        project.setPname(
                project.getPname().equals(projectRequest.getPname()) ? project.getPname() : projectRequest.getPname());
        project.setUrl(project.getUrl().equals(projectRequest.getUrl()) ? project.getUrl() : projectRequest.getUrl());
        project.setLead(
                project.getLead().equals(projectRequest.getLead()) ? project.getLead() : projectRequest.getLead());
        project.setDescription(
                project.getDescription().equals(projectRequest.getDescription())
                        ? project.getDescription()
                        : projectRequest.getDescription());
        project.setPkey(
                project.getPkey().equals(projectRequest.getPkey()) ? project.getPkey() : projectRequest.getPkey());
        project.setPcounter(
                project.getPcounter().equals(projectRequest.getPcounter())
                        ? project.getPcounter()
                        : projectRequest.getPcounter());
        project.setAssignee_type(
                project.getAssignee_type().equals(projectRequest.getAssignee_type())
                        ? project.getAssignee_type()
                        : projectRequest.getAssignee_type());
        project.setAvatar_id(
                project.getAvatar_id().equals(projectRequest.getAvatar_id())
                        ? project.getAvatar_id()
                        : projectRequest.getAvatar_id());
        project.setOriginal_key(
                project.getOriginal_key().equals(projectRequest.getOriginal_key())
                        ? project.getOriginal_key()
                        : projectRequest.getOriginal_key());
        project.setProject_type_id(
                project.getProject_type_id().equals(projectRequest.getProject_type_id())
                        ? project.getProject_type_id()
                        : projectRequest.getProject_type_id());
        project.setUpdated_at(Timestamp.from(Instant.now()));
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    // update project role (change project role)
    public UserResponse updateProjectRole(Integer id, ProjectUserRequest projectUserRequest) {
        ProjectUser projectUser = projectUserRepository.findById(id).orElseThrow();
        projectUser.setProject_role_id(projectUser.getProject_role_id().equals(projectUserRequest.getRoleId()) ? projectUser.getProject_role_id() : projectUserRequest.getRoleId() );
        projectUser = projectUserRepository.save(projectUser);
        ApiResponse<UserResponse> apiResponse = userClient.getUserById(projectUser.getUserId());
        return apiResponse.getResult();
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    public List<ProjectResponse> getAllProjects() {
        var projects = projectRepository.findAll().stream().toList();
        return projects.stream().map(projectMapper::toProjectResponse).toList();
    }

    public void deleteProject(Integer projectId) {
        Project project =
                projectRepository.findById(projectId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        projectRepository.delete(project);
    }

    // TODO: Delete a user from a project
    public void deleteUserFromProject(Integer id) {
        ProjectUser projectUser = projectUserRepository.findByUserId(id).orElseThrow();
        projectUserRepository.delete(projectUser);
    }

    // ADD MEMBERS
//    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    public UserResponse addUserToProject(ProjectUserRequest projectUserRequest) {
        // get a user from identity (use useClient)
        ApiResponse<UserResponse> apiResponse = userClient.getUserById(projectUserRequest.getUserId());
        // get a role from identity (use useClient)
        ApiResponse<RoleResponse> apiRoleResponse = userClient.getProjectRole(projectUserRequest.getRoleId());

        Project project = projectRepository.findById(projectUserRequest.getProjectId()).orElseThrow();

        ProjectUser projectUser = new ProjectUser();
        projectUser.setProject(project);
        projectUser.setUserId(projectUserRequest.getUserId());
        projectUser.setProject_role_id(projectUserRequest.getRoleId());
        projectUserRepository.save(projectUser);

        // trả về lỗi
        return apiResponse.getResult();
    }

    public ProjectIssueResponse getProject(Integer id) {
        Project project = projectRepository.findById(id).orElseThrow();
        ApiResponse<List<IssueResponse>> apiResponse = issueClient.getIssueByProjectId(project.getId());
        Set<IssueResponse> issueResponses = new HashSet<>(apiResponse.getResult());

        log.info("list size {}", apiResponse.getResult().size());

        return projectMapper.toProjectIssueResponse(project, issueResponses);
    }

}

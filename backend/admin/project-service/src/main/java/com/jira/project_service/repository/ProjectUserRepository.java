package com.jira.project_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.project_service.entity.ProjectUser;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Integer> {
    Optional<ProjectUser> findByUserId(Integer userId);
}

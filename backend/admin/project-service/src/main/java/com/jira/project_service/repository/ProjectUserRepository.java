package com.jira.project_service.repository;

import com.jira.project_service.entity.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Integer> {
    Optional<ProjectUser> findByUserId(Integer userId);
}

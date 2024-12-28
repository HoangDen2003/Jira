package com.jira.project_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jira.project_service.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {}

package com.jira.project_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.project_service.entity.ProjectType;

@Repository
public interface ProjectTypeRepository extends JpaRepository<ProjectType, Integer> {}

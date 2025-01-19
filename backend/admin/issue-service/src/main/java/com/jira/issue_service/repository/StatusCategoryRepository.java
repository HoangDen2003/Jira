package com.jira.issue_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.issue_service.entity.StatusCategory;

@Repository
public interface StatusCategoryRepository extends JpaRepository<StatusCategory, Integer> {}

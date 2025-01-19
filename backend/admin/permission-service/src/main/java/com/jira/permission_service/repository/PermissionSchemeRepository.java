package com.jira.permission_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.permission_service.entity.PermissionScheme;

@Repository
public interface PermissionSchemeRepository extends JpaRepository<PermissionScheme, Integer> {}

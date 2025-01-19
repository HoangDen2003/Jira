package com.jira.permission_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jira.permission_service.entity.SchemePermission;

public interface SchemePermissionRepository extends JpaRepository<SchemePermission, Integer> {}

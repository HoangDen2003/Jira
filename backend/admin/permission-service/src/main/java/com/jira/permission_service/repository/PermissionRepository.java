package com.jira.permission_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jira.permission_service.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {}

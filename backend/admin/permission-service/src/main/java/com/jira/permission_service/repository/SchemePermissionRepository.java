package com.jira.permission_service.repository;

import com.jira.permission_service.entity.SchemePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchemePermissionRepository extends JpaRepository<SchemePermission, Integer> {}

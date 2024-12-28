package com.jira.permission_service.repository;

import com.jira.permission_service.entity.PermissionScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionSchemeRepository extends JpaRepository<PermissionScheme, Integer> {
}

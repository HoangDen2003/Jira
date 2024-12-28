package com.jira.permission_service.repository;

import com.jira.permission_service.entity.PermissionAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionAttributeRepository extends JpaRepository<PermissionAttribute, Integer> {
}

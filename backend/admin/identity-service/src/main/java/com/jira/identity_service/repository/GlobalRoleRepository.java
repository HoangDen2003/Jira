package com.jira.identity_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.identity_service.entity.GlobalRole;

@Repository
public interface GlobalRoleRepository extends JpaRepository<GlobalRole, Integer> {}

package com.jira.identity_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jira.identity_service.entity.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, Integer> {}

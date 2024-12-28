package com.jira.identity_service.repository;

import java.util.Optional;

import com.jira.identity_service.dto.response.UserTokenResponse;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jira.identity_service.entity.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}

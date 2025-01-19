package com.jira.identity_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.identity_service.entity.Profile;

@Repository
public interface ProfileReposity extends JpaRepository<Profile, Integer> {
    Optional<Profile> findProfileWithUserByUserId(Integer userId);
}

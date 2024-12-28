package com.jira.identity_service.repository;

import com.jira.identity_service.entity.Profile;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileReposity extends JpaRepository<Profile, Integer> {
    Optional<Profile> findProfileWithUserByUserId(Integer userId);
}

package com.jira.issue_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.issue_service.entity.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {
    List<Issue> findIssueByProjectId(Integer projectId);

    List<Issue> findIssueByProjectIdAndSummaryContaining(Integer projectId, String text);

    Issue findIssueByProjectIdAndId(Integer projectId, Integer id);
}

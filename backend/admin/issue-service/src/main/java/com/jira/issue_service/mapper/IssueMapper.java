package com.jira.issue_service.mapper;

import org.mapstruct.Mapper;

import com.jira.issue_service.dto.request.IssueRequest;
import com.jira.issue_service.dto.response.IssueResponse;
import com.jira.issue_service.entity.Issue;

@Mapper(componentModel = "spring")
public interface IssueMapper {
    Issue toIssue(IssueRequest issueRequest);

    IssueResponse toIssueResponse(Issue issue);
}

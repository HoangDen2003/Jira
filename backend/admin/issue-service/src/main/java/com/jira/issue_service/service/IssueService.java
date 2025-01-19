package com.jira.issue_service.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jira.issue_service.dto.request.IssueRequest;
import com.jira.issue_service.dto.response.IssueResponse;
import com.jira.issue_service.entity.Issue;
import com.jira.issue_service.exception.AppException;
import com.jira.issue_service.exception.ErrorCode;
import com.jira.issue_service.mapper.IssueMapper;
import com.jira.issue_service.repository.IssueRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@Slf4j
public class IssueService {
    IssueRepository issueRepository;
    IssueMapper issueMapper;

    public IssueResponse createIssue(IssueRequest issueRequest) {
        Issue issue = issueMapper.toIssue(issueRequest);
        issue = issueRepository.save(issue);
        return issueMapper.toIssueResponse(issue);
    }

    public IssueResponse updateIssue(Integer id, IssueRequest issueRequest) {
        Issue issue = issueRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        issue.setPkey(issue.getPkey().equals(issueRequest.getPkey()) ? issue.getPkey() : issueRequest.getPkey());
        issue.setIssue_num(
                issue.getIssue_num().equals(issueRequest.getIssue_num())
                        ? issue.getIssue_num()
                        : issueRequest.getIssue_num());
        issue.setProjectId(
                issue.getProjectId().equals(issueRequest.getProjectId())
                        ? issue.getProjectId()
                        : issueRequest.getProjectId());
        issue.setWorkflow_id(
                issue.getWorkflow_id().equals(issueRequest.getWorkflow_id())
                        ? issue.getWorkflow_id()
                        : issueRequest.getWorkflow_id());
        issue.setIssue_type_id(
                issue.getIssue_type_id().equals(issueRequest.getIssue_type_id())
                        ? issue.getIssue_type_id()
                        : issueRequest.getIssue_type_id());
        issue.setIssue_status_id(
                issue.getIssue_status_id().equals(issueRequest.getIssue_status_id())
                        ? issue.getIssue_status_id()
                        : issueRequest.getIssue_status_id());
        issue.setReporter(
                issue.getReporter().equals(issueRequest.getReporter())
                        ? issue.getReporter()
                        : issueRequest.getReporter());
        issue.setAssignee(
                issue.getAssignee().equals(issueRequest.getAssignee())
                        ? issue.getAssignee()
                        : issueRequest.getAssignee());
        issue.setCreator(
                issue.getCreator().equals(issueRequest.getCreator()) ? issue.getCreator() : issueRequest.getCreator());
        issue.setSummary(
                issue.getSummary().equals(issueRequest.getSummary()) ? issue.getSummary() : issueRequest.getSummary());
        issue.setDescription(
                issue.getDescription().equals(issueRequest.getDescription())
                        ? issue.getDescription()
                        : issueRequest.getDescription());
        issue.setEnvironment(
                issue.getEnvironment().equals(issueRequest.getEnvironment())
                        ? issue.getEnvironment()
                        : issueRequest.getEnvironment());
        issue.setWatches(
                issue.getWatches().equals(issueRequest.getWatches()) ? issue.getWatches() : issueRequest.getWatches());
        issue.setSecurity(
                issue.getSecurity().equals(issueRequest.getSecurity())
                        ? issue.getSecurity()
                        : issueRequest.getSecurity());
        issue.setArchived_by(
                issue.getArchived_by().equals(issueRequest.getArchived_by())
                        ? issue.getArchived_by()
                        : issueRequest.getArchived_by());
        issue.setArchived(
                issue.getArchived().equals(issueRequest.getArchived())
                        ? issue.getArchived()
                        : issueRequest.getArchived());
        issue.setDue_date(
                issue.getDue_date().equals(issueRequest.getDue_date())
                        ? issue.getDue_date()
                        : issueRequest.getDue_date());
        issue.setArchived_date(
                issue.getArchived_date().equals(issueRequest.getArchived_date())
                        ? issue.getArchived_date()
                        : issueRequest.getArchived_date());
        issue.setUpdated_at(Timestamp.from(Instant.now()));

        issue = issueRepository.save(issue);
        return issueMapper.toIssueResponse(issue);
    }

    // TODO: get all issues by with projectId
    public List<IssueResponse> getIssuesByProjectId(Integer projectId, String text) {
        log.info("text issue {}", text);
        if (text != null && !text.isEmpty()) {
            List<Issue> issues = issueRepository.findIssueByProjectIdAndSummaryContaining(projectId, text).stream()
                    .toList();
            return issues.stream().map(issueMapper::toIssueResponse).toList();
        }
        List<Issue> issues =
                issueRepository.findIssueByProjectId(projectId).stream().toList();
        return issues.stream().map(issueMapper::toIssueResponse).toList();
    }

    public List<IssueResponse> getAllIssues() {
        var issues = issueRepository.findAll().stream().toList();
        return issues.stream().map(issueMapper::toIssueResponse).toList();
    }

    public void deleteIssue(Integer id) {
        Issue issue = issueRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        issueRepository.delete(issue);
    }

    public IssueResponse getIssueById(Integer id, Integer projectId) {
        Issue issue = issueRepository.findIssueByProjectIdAndId(projectId, id);
        return issueMapper.toIssueResponse(issue);
    }
}

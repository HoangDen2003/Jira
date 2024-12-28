package com.jira.issue_service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IssueRequest {
    String pkey;
    String summary;
    String description;
    Integer issue_num;
    Integer projectId;
    Integer workflow_id;
    Integer issue_type_id;
    Integer issue_status_id;
    String reporter;
    String assignee;
    String creator;
    String environment;
    Integer watches;
    Integer security;
    String archived_by;
    String archived;
    Timestamp due_date;
    Timestamp archived_date;
}

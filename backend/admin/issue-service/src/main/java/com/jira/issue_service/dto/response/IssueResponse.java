package com.jira.issue_service.dto.response;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IssueResponse {
    String pkey;
    String summary;
    String description;
    Integer issue_num;
    Integer projectId;
    Integer workflowId;
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
    Timestamp created_at;
    Timestamp updated_at;
}

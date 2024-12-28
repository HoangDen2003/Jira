package com.jira.project_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.Set;

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

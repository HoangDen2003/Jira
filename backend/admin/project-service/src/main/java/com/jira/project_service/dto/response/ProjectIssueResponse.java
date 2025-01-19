package com.jira.project_service.dto.response;

import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectIssueResponse {
    ProjectResponse project;
    Set<IssueResponse> issues;
}

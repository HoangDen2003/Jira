package com.jira.issue_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IssueStatusResponse {
    String name;
    String icon_url;
    String status_category;
    String description;
    Integer sequence;
}

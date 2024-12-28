package com.jira.issue_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IssueTypeResponse {
    String name;
    String style;
    String icon_url;
    String description;
    Integer avatar_id;
}

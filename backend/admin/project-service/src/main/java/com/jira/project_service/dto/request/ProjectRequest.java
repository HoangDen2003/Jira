package com.jira.project_service.dto.request;

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
public class ProjectRequest {
    String pname;
    String url;
    String lead;
    String description;
    Integer pkey;
    Integer pcounter;
    String assignee_type;
    Integer avatar_id;
    String original_key;
    Integer project_type_id;
}

package com.jira.project_service.dto.response;

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
public class ProjectResponse {
    Integer id;
    String pname;
    String url;
    String lead;
    String description;
    Integer pkey;
    Integer pcounter;
    String assignee_type;
    Integer avatar;
    String original_key;
    Integer project_type_id;
    Timestamp created_at;
    Timestamp updated_at;
}

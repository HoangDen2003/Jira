package com.jira.project_service.dto.response;

import javax.management.relation.Role;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectUserRoleReponse {
    Integer Id;
    String pname;
    String url;
    String lead;
    String description;
    Integer pkey;
    Integer pcounter;
    String assignee_type;
    Integer avatar;
    UserResponse user;
    Role role;
}

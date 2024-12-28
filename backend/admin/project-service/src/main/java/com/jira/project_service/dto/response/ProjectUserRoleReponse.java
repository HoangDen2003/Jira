package com.jira.project_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.management.relation.Role;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectUserRoleReponse {
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

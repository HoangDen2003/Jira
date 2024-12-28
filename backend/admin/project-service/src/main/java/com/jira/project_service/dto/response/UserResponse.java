package com.jira.project_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    // Trả về all users
    Integer id;
    String email;
    ProfileResponse profile;
    Set<RoleResponse> roles;
}

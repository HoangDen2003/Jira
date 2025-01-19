package com.jira.project_service.dto.response;

import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

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

package com.jira.identity_service.dto.response;

import java.util.Set;

import com.jira.identity_service.entity.InvalidatedToken;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserTokenResponse {
    // Trả về khi người dùng đăng nhập
    String email;
    Set<RoleResponse> roles;
    InvalidatedToken invalidatedToken;
}

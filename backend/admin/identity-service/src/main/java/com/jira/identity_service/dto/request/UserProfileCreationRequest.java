package com.jira.identity_service.dto.request;

import java.sql.Date;

import com.jira.identity_service.entity.GlobalRole;
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
public class UserProfileCreationRequest {
    // Đăng Ký
    String email;
    String password;
    String full_name;
    String avatar;
    String phone;
    String address;
    Date birthday;
    String gender;
    Integer global_role_id;
}

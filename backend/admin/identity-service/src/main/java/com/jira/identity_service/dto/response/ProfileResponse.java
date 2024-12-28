package com.jira.identity_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileResponse {
    String full_name;
    String avatar;
    String phone;
    String address;
    Date birthday;
    String gender;
}

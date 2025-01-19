package com.jira.identity_service.dto.response;

import java.sql.Date;

import lombok.*;
import lombok.experimental.FieldDefaults;

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

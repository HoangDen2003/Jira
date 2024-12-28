package com.jira.permission_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SchemePermissionResponse {
    Integer schemeId;
    Integer permissionId;
    String permissionParameter;
    String permissionType;
    String permissionScope;
    String permissionKey;
}

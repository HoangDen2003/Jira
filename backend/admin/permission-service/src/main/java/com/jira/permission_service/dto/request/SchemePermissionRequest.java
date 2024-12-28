package com.jira.permission_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SchemePermissionRequest {
    Integer schemeId;
    Integer permissionId;
    String permissionParameter;
    String permissionType;
    String permissionScope;
    String permissionKey;

}

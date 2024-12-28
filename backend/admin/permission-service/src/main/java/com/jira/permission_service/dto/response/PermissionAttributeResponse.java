package com.jira.permission_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionAttributeResponse {
    Integer permissionId;
    String attributeKey;
    String attributeValue;
}

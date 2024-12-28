package com.jira.permission_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionAttributeRequest {
    Integer permissionId;
    String attributeKey;
    String attributeValue;
}

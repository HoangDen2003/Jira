package com.jira.permission_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "jira_permission_attributes")
public class PermissionAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;
    @Column(name = "permission_id")
    Integer permissionId;
    @Column(name = "attribute_key")
    String attributeKey;
    @Column(name = "attribute_value")
    String attributeValue;
}

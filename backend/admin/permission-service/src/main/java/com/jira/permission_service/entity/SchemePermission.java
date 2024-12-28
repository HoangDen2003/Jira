package com.jira.permission_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "jira_scheme_permissions")
public class SchemePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;
    @Column(name = "permission_scheme_id")
    Integer schemeId;
    @Column(name = "permission_id")
    Integer permissionId;
    @Column(name = "permission_type")
    String permissionType;
    @Column(name = "permission_parameter")
    String permissionParameter;
    @Column(name = "permission_key")
    String permissionKey;
    @Column(name = "permission_scope")
    String permissionScope;
}

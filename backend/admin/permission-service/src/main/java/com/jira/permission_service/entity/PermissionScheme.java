package com.jira.permission_service.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "jira_permission_schemes")
public class PermissionScheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;

    String name;
    String description;
}

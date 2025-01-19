package com.jira.project_service.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "jira_project_users")
public class ProjectUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "user_id")
    Integer userId;

    @OneToOne
    Project project;

    Integer project_role_id;
}

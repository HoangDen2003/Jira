package com.jira.project_service.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "jira_projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;

    String pname;
    String url;
    String lead;
    String description;
    Integer pkey;
    Integer pcounter;
    String assignee_type;
    Integer avatar_id;
    String original_key;
    Integer project_type_id;
    Timestamp created_at;
    Timestamp updated_at;
}

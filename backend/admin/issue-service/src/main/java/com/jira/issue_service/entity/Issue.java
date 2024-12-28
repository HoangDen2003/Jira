package com.jira.issue_service.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jira_issues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;

    String pkey;
    String summary;
    String description;
    Integer issue_num;
    @Column(name = "project_id")
    Integer projectId;
    Integer workflow_id;
    Integer issue_type_id;
    Integer issue_status_id;
    String reporter;
    String assignee;
    String creator;
    String environment;
    Integer watches;
    Integer security;
    String archived_by;
    String archived;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Timestamp due_date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Timestamp archived_date;
    Timestamp created_at;
    Timestamp updated_at;
}

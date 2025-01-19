package com.jira.issue_service.entity;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "jira_issue_status")
public class IssueStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name; // todo(Open, reopened, ...), in progress(building, pending, ...), done(resolved, closed, ...)
    String icon_url;
    String description;
    String status_category; // todo, in progress, done
    Integer sequence;
}

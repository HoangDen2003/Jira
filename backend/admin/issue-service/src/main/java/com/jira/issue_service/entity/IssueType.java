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
@Table(name = "jira_issue_types")
public class IssueType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;        // Epic, Bug, Story, ...
    String style;       // standard type (level 0) or sub-task type (level - 1)
    String icon_url;
    String description;
    Integer avatar_id;
}

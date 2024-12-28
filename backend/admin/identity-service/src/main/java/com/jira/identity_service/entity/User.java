package com.jira.identity_service.entity;

import java.sql.*;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "jira_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String email;
    String password;

    Timestamp created_at;
    Timestamp updated_at;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    GlobalRole global_role;
}

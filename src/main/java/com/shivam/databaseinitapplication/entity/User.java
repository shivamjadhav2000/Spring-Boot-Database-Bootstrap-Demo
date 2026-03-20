package com.shivam.databaseinitapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false,unique = true)
    private Long id;

    @Column(name ="first_name")
    private String firstname;

    @Column(name ="last_name")
    private String lastname;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="username", unique = true, nullable = false)
    private String username;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="is_global",nullable = false)
    private Boolean isGlobal = false;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Column(name = "system_protected", nullable = false)
    private boolean systemProtected = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    @JsonIgnore
    private Role role;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}

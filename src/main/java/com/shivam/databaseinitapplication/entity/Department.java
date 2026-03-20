package com.shivam.databaseinitapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id",nullable = false, unique = true)
    private Long id;

    @Column(name="name", unique = true)
    private String name;
    @Column(name = "website")
    private String website;
    @Column(name = "email", length = 60)
    private String email;
    @Column(name = "phone",length = 20)
    private String phone;
    @Column(name = "address", length = 300)
    private String address;
    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
    @Builder.Default
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
    @Builder.Default
    @Column(name = "is_root", nullable = false)
    private boolean isRoot = false;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private User createdBy;
}

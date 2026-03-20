package com.shivam.databaseinitapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id",nullable = false, unique = true)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name ="can_view_department")
    private Boolean canViewDepartment = false;

    @Column(name ="can_edit_department")
    private Boolean canEditDepartment = false;

    @Column(name ="can_delete_department")
    private Boolean canDeleteDepartment = false;

    @Column(name ="is_admin")
    private Boolean isAdmin = false;

    @Column(name ="is_super_admin")
    private Boolean isSuperAdmin = false;

    @Column(name ="description")
    private String description;
}

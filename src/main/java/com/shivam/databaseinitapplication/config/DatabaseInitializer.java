package com.shivam.databaseinitapplication.config;

import com.shivam.databaseinitapplication.entity.Department;
import com.shivam.databaseinitapplication.entity.Role;
import com.shivam.databaseinitapplication.repository.DepartmentRepository;
import com.shivam.databaseinitapplication.repository.RoleRepository;
import com.shivam.databaseinitapplication.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.shivam.databaseinitapplication.entity.User;


@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseInitializer implements ApplicationRunner {

    private final JdbcTemplate jdbcTemplate;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        seedDefaultRoles();
        seedDefaultDepartmentAndAdmin();
    }




    private void seedDefaultRoles() {
        List<Role> defaults = Arrays.asList(
                Role.builder()
                        .name("SUPER_ADMIN")
                        .canViewDepartment(true)
                        .canEditDepartment(true)
                        .canDeleteDepartment(true)
                        .isAdmin(true)
                        .isSuperAdmin(true)
                        .description("Root super admin")
                        .build(),
                Role.builder()
                        .name("ROLE_ADMIN")
                        .canViewDepartment(true)
                        .canEditDepartment(true)
                        .canDeleteDepartment(true)
                        .isAdmin(true)
                        .isSuperAdmin(false)
                        .description("Admin user")
                        .build(),
                Role.builder()
                        .name("ROLE_STANDARD")
                        .canViewDepartment(true)
                        .canEditDepartment(true)
                        .canDeleteDepartment(false)
                        .isAdmin(false)
                        .isSuperAdmin(false)
                        .description("Standard user")
                        .build(),
                Role.builder()
                        .name("ROLE_VIEWER")
                        .canViewDepartment(true)
                        .canEditDepartment(false)
                        .canDeleteDepartment(false)
                        .isAdmin(false)
                        .isSuperAdmin(false)
                        .description("Read-only user")
                        .build()
        );

        for (Role role : defaults) {
            roleRepository.findByName(role.getName()).orElseGet(() -> {
                log.info("Seeding default role: {}", role.getName());
                return roleRepository.save(role);
            });
        }
    }

    private void seedDefaultDepartmentAndAdmin() {

        Department department = departmentRepository.findAll().stream()
                .findFirst()
                .orElseGet(() -> {
                    log.info("Seeding default department: Shivam Jadhav Technologies");
                    Department created = Department.builder()
                            .name("Shivam Jadhav Technologies")
                            .email("admin@company.com")
                            .website("https://example.com")
                            .phone("1234567890")
                            .address("123 Admin St")
                            .isRoot(true)
                            .isActive(true)
                            .isDeleted(false)
                            .build();
                    return departmentRepository.save(created);
                });

        Role superAdmin = roleRepository.findByName("SUPER_ADMIN")
                .orElseThrow(() -> new IllegalStateException("SUPER_ADMIN role missing after bootstrap"));

        User admin = null;
        if (userRepository.count() == 0) {
            log.info("Seeding default admin user: admin");
            User created = User.builder()
                    .username("admin")
                    .firstname("Admin")
                    .lastname("User")
                    .email("admin@company.com")
                    .password("admin123")
                    .isGlobal(true)
                    .isActive(true)
                    .isDeleted(false)
                    .systemProtected(true)
                    .role(superAdmin)
                    .department(department)
                    .build();
            admin = userRepository.save(created);
        } else {
            log.info("Admin seed skipped: users already exist.");
        }

        if (department.getCreatedBy() == null && admin != null) {
            department.setCreatedBy(admin);
            departmentRepository.save(department);
        }
    }




}

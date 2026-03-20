package com.shivam.databaseinitapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.shivam.databaseinitapplication.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Department, Long> {
    List<Department> findByIsActiveTrueAndIsDeletedFalseOrderByNameAsc();
    List<Department> findByIsActiveTrueAndIsDeletedFalseOrderByNameDesc();
    // Only active (regardless of deleted)
    List<Department> findByIsActiveTrueOrderByNameAsc();
    List<Department> findByIsActiveTrueAndIsDeletedFalseOrderByIdAsc();
    List<Department> findByIsActiveTrueOrderByIdAsc();
    List<Department> findByIsDeletedFalseOrderByIdAsc();
    List<Department> findAllByOrderByIdAsc();
    // Fallback for custom sorting
    List<Department> findAllByOrderByNameAsc();
    Optional<Department> findByIdAndIsActiveTrueAndIsDeletedFalse(Long id);
    // Only not deleted (regardless of active)
    List<Department> findByIsDeletedFalseOrderByNameAsc();
    boolean existsByName(String name);
    Optional<Department> findByName(String name);
    boolean existsByNameAndIsActiveTrueAndIsDeletedFalse(String name);
}

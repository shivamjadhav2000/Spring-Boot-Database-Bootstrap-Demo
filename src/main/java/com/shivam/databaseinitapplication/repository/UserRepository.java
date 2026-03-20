package com.shivam.databaseinitapplication.repository;

import com.shivam.databaseinitapplication.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;


import com.shivam.databaseinitapplication.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
List<User> findAll();
}

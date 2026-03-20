-- =========================================
-- 1️⃣ ROLES TABLE
-- =========================================
CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) UNIQUE NOT NULL,
                       can_view_department BOOLEAN DEFAULT FALSE,
                       can_edit_department BOOLEAN DEFAULT FALSE,
                       can_delete_department BOOLEAN DEFAULT FALSE,
                       is_admin BOOLEAN DEFAULT FALSE,
                       is_super_admin BOOLEAN DEFAULT FALSE,
                       description TEXT
);

-- =========================================
-- 2️⃣ DEPARTMENTS TABLE (NO FK YET)
-- =========================================
CREATE TABLE departments (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(255) NOT NULL,
                             website VARCHAR(255),
                             email VARCHAR(255),
                             phone VARCHAR(50),
                             address TEXT,
                             is_active BOOLEAN DEFAULT TRUE,
                             is_deleted BOOLEAN DEFAULT FALSE,
                             is_root BOOLEAN DEFAULT FALSE,

                             created_by BIGINT NULL,

                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- 3️⃣ USERS TABLE
-- =========================================
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(100) NOT NULL,
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,

                       is_active BOOLEAN DEFAULT TRUE,
                       is_deleted BOOLEAN DEFAULT FALSE,

                       role_id BIGINT NOT NULL,
                       department_id BIGINT NOT NULL,

                       is_global BOOLEAN DEFAULT FALSE,
                       system_protected BOOLEAN DEFAULT FALSE,

                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- 5️⃣ INDEXES (SOFT DELETE SAFE)
-- =========================================
CREATE UNIQUE INDEX users_email_active_unique
    ON users(email)
    WHERE is_deleted = false;

CREATE UNIQUE INDEX users_username_active_unique
    ON users(username)
    WHERE is_deleted = false;

CREATE UNIQUE INDEX departments_name_active_unique
    ON departments(name)
    WHERE is_deleted = false;
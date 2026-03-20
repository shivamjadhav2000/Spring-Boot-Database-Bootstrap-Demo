-- =========================================
-- 1️⃣ USERS → ROLES FK
-- =========================================
ALTER TABLE users
    ADD CONSTRAINT fk_users_roles
        FOREIGN KEY (role_id) REFERENCES roles(id);

-- =========================================
-- 2️⃣ USERS → DEPARTMENTS FK
-- =========================================
ALTER TABLE users
    ADD CONSTRAINT fk_users_departments
        FOREIGN KEY (department_id) REFERENCES departments(id);

-- =========================================
-- 3️⃣ DEPARTMENTS → USERS (created_by)
-- =========================================
ALTER TABLE departments
    ADD CONSTRAINT fk_departments_created_by
        FOREIGN KEY (created_by) REFERENCES users(id);
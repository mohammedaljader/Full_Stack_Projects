package org.project.joboffers.JPARepository;

import org.project.joboffers.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, String> {
    boolean existsByRoleName(String roleName);
    Role findByRoleName(String roleName);
}

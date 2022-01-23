package org.project.joboffers.DALInterfaces;

import org.project.joboffers.Model.Role;


public interface IRoleDAL {
    void addRole(Role role);
    Role getRoleByRoleName(String roleName);
    boolean existsByRoleName(String roleName);
    void deleteRole(Role role);
    void updateRole(Role role);
}

package org.project.joboffers.DAL;

import org.project.joboffers.DALInterfaces.IRoleDAL;
import org.project.joboffers.JPARepository.RoleRepo;
import org.project.joboffers.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAL implements IRoleDAL {
    private final RoleRepo roleRepo;

    @Autowired
    public RoleDAL(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void addRole(Role role) {
        roleRepo.save(role);
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleRepo.findByRoleName(roleName);
    }

    @Override
    public boolean existsByRoleName(String roleName) {
        return roleRepo.existsByRoleName(roleName);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepo.delete(role);
    }

    @Override
    public void updateRole(Role role) {
        roleRepo.save(role);
    }
}

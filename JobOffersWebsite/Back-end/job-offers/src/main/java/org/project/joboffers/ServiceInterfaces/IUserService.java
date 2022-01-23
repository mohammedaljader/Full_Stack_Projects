package org.project.joboffers.ServiceInterfaces;

import org.project.joboffers.DTO.RoleDTO;
import org.project.joboffers.DTO.UserDTO;
import org.project.joboffers.Model.Role;
import org.project.joboffers.Model.User;

import java.util.List;

public interface IUserService {
    boolean addUser(UserDTO userDTO);
    boolean addRole(RoleDTO roleDTO);
    UserDTO getUserByUsername(String username);
    List<UserDTO> getAllUsers();
    RoleDTO getRole(String roleName);
    boolean editUser(UserDTO userDTO);
    boolean deleteUser(String username);
    boolean editPassword(String username, String password);
    boolean updateUserRole(String username, String roleName); // This functionality is for Admin: The Admin is the one who can upgrade the users
}

package org.project.joboffers.Convertor;

import org.project.joboffers.DTO.RoleDTO;
import org.project.joboffers.DTO.UserDTO;
import org.project.joboffers.Model.Role;
import org.project.joboffers.Model.User;

import java.util.List;

public interface IUserConvertor {
    UserDTO convertToDTO(User user);
    User convertToModel(UserDTO userDTO, Role role);
    List<UserDTO> convertListToDto(List<User> users);
    RoleDTO convertRoleToDTO(Role role);
    Role convertRoleToModel(RoleDTO roleDTO);

}

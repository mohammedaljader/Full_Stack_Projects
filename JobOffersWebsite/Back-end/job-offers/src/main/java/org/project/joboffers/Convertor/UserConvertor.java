package org.project.joboffers.Convertor;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.project.joboffers.DTO.RoleDTO;
import org.project.joboffers.DTO.UserDTO;
import org.project.joboffers.Model.Role;
import org.project.joboffers.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConvertor implements IUserConvertor {
    private final ModelMapper modelMapper;

    @Autowired
    public UserConvertor(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO convertToDTO(User user) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public User convertToModel(UserDTO userDTO, Role role) {
        return new User(userDTO.getUserId(),
                userDTO.getName(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getAddress(),
                role);
    }

    @Override
    public List<UserDTO> convertListToDto(List<User> users) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO convertRoleToDTO(Role role) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(role, RoleDTO.class);
    }

    @Override
    public Role convertRoleToModel(RoleDTO roleDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(roleDTO, Role.class);
    }


}

package org.project.joboffers.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.joboffers.Convertor.IUserConvertor;
import org.project.joboffers.DALInterfaces.IRoleDAL;
import org.project.joboffers.DALInterfaces.IUserDAL;
import org.project.joboffers.DTO.RoleDTO;
import org.project.joboffers.DTO.UserDTO;
import org.project.joboffers.Model.Role;
import org.project.joboffers.Model.User;
import org.project.joboffers.ServiceInterfaces.IUserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService, UserDetailsService {
    private final IUserDAL userDAL;
    private final IRoleDAL roleDAL;
    private final PasswordEncoder passwordEncoder;
    private final IUserConvertor userConvertor;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAL.getUserByUsername(username);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }
        log.info("User found in the database: {}", username);
        Role role = user.getRole();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public boolean addUser(UserDTO userDTO) {
        if(!userDAL.existsByEmail(userDTO.getEmail()) && !userDAL.existsByUsername(userDTO.getUsername())){
            log.info("Saving new user {} to the database", userDTO.getName());
            Role role = roleDAL.getRoleByRoleName(userDTO.getRoleName());
            User user = new User(userDTO.getName(),
                    userDTO.getUsername(),
                    userDTO.getEmail(),
                    userDTO.getPassword(),
                    userDTO.getAddress(),
                    role);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAL.addUser(user);
            return true;
        }
        else {
            log.info("Username or Email already exists!!");
            return false;
        }
    }

    @Override
    public boolean addRole(RoleDTO roleDTO) {
        if(!roleDAL.existsByRoleName(roleDTO.getRoleName())){
            log.info("Saving new role {} to the database", roleDTO.getRoleName());
            Role role = new Role(roleDTO.getRoleName());
            roleDAL.addRole(role);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userDAL.getUserByUsername(username);
        if(user != null){
            log.info("Fetching user {}", username);
            return userConvertor.convertToDTO(user);
        }
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userDAL.getAllUsers();
        if(users != null){
            log.info("Fetching all users");
            return userConvertor.convertListToDto(users);
        }
        return null;
    }

    @Override
    public RoleDTO getRole(String roleName) {
        Role role = roleDAL.getRoleByRoleName(roleName);
        return new RoleDTO(role.getRoleId(), role.getRoleName());
    }

    @Override
    public boolean editUser(UserDTO userDTO) {
        User oldUser = userDAL.getUserByUserId(userDTO.getUserId());
        if(Objects.equals(oldUser.getEmail(), userDTO.getEmail()) || !userDAL.existsByEmail(userDTO.getEmail())) {
            User user = userConvertor.convertToModel(userDTO , oldUser.getRole());
            user.setUsername(oldUser.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userDAL.editUser(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(String username) {
        User user = userDAL.getUserByUsername(username);
        if(user != null && !Objects.equals(user.getRole().getRoleName(), "Admin")){
            userDAL.deleteUser(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean editPassword(String username, String password) {
        User user = userDAL.getUserByUsername(username);
        if(user != null){
            userDAL.editPassword(username, passwordEncoder.encode(password));
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUserRole(String username, String roleName) {
        User user = userDAL.getUserByUsername(username);
        Role role = roleDAL.getRoleByRoleName(roleName);
        if(user != null && role != null && !Objects.equals(user.getRole().getRoleName(), "Admin")){
            user.setRole(role);
            userDAL.editUser(user);
            return true;
        }
        return false;
    }

}

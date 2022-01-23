package org.project.joboffers.ServiceMockTests;



import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.project.joboffers.Convertor.UserConvertor;
import org.project.joboffers.DALInterfaces.IRoleDAL;
import org.project.joboffers.DALInterfaces.IUserDAL;
import org.project.joboffers.DTO.RoleDTO;
import org.project.joboffers.DTO.UserDTO;
import org.project.joboffers.Model.Role;
import org.project.joboffers.Model.User;
import org.project.joboffers.Service.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private IUserDAL userDAL;
    @Mock
    private IRoleDAL roleDAL;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;


    @BeforeEach
    void setUp() {
        userService = new UserService(userDAL, roleDAL, passwordEncoder, new UserConvertor(new ModelMapper()));
    }

    @Test
    void loadUserByUsername() {
        User user = new User("test", "test","test", "test", "test", new Role("test"));
        lenient().when(userDAL.getUserByUsername("test")).thenReturn(user);

        UserDetails expected = userService.loadUserByUsername("test");

        Assertions.assertEquals(user.getPassword(), expected.getPassword());
        Assertions.assertEquals(expected.getAuthorities().size(), 1);
    }

    @Test
    @DisplayName("If the user enter invalid username than get not found exception.")
    void loadUserByUsername_WithInvalid_ReturnNull(){
        lenient().when(userDAL.getUserByUsername(anyString())).thenReturn(null);
        UsernameNotFoundException thrown = Assertions.assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("test"));

        Assertions.assertEquals("User not found in the database", thrown.getMessage());
    }

    @Test
    void saveUser() {
        //Given
        Role role = new Role(null, "Job_seeker");
        UserDTO userDTO = new UserDTO("test","test","test", "test", "test", "Job_seeker");
        //When
        when(roleDAL.getRoleByRoleName(role.getRoleName())).thenReturn(role);
        userService.addUser(userDTO);
        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userDAL).addUser(userArgumentCaptor.capture());

        User captureUser = userArgumentCaptor.getValue();

        Assertions.assertEquals(userDTO.getEmail(), captureUser.getEmail());
        Assertions.assertEquals(userDTO.getName(), captureUser.getName());
        Assertions.assertEquals(userDTO.getUsername(), captureUser.getUsername());
        Assertions.assertEquals(userDTO.getAddress(), captureUser.getAddress());
        Assertions.assertEquals(userDTO.getRoleName(), captureUser.getRole().getRoleName());
    }

    @Test
    void saveRole() {
        //Given
        RoleDTO role = new RoleDTO("test");
        //when
//        RoleDTO roleDTO = new RoleDTO(null, "test");
        userService.addRole(role);
        //then
        ArgumentCaptor<Role> roleArgumentCaptor = ArgumentCaptor.forClass(Role.class);
        verify(roleDAL).addRole(roleArgumentCaptor.capture());
        Role captureRole = roleArgumentCaptor.getValue();
        Assertions.assertEquals(role.getRoleName(), captureRole.getRoleName());
    }

    @Test
    void getUserByUsername() {
        //Given
        User user = new User("test", "test","test", "test", "test", new Role("test"));
       //When
        lenient().when(userDAL.getUserByUsername("test")).thenReturn(user);
        //then
        UserDTO userToCheck = userService.getUserByUsername("test");

        Assertions.assertEquals(userToCheck.getPassword(), user.getPassword());
        Assertions.assertEquals(userToCheck.getAddress(), user.getAddress());
        Assertions.assertEquals(userToCheck.getEmail(), user.getEmail());
        Assertions.assertEquals(userToCheck.getRoleName(), user.getRole().getRoleName());
    }

    @Test
    void getUsers() {
        List<User> users = List.of(
                new User("test1", "test1","test1", "test1", "test1", new Role("test1")),
                new User("test2", "test2","test2", "test2", "test2", new Role("test2")),
                new User("test3", "test3","test3", "test3", "test3", new Role("test3"))
        );
        lenient().when(userDAL.getAllUsers()).thenReturn(users);

        List<UserDTO> usersToCheck = userService.getAllUsers();

        Assertions.assertEquals(usersToCheck.get(0).getName(), users.get(0).getName());
        Assertions.assertEquals(usersToCheck.get(1).getName(), users.get(1).getName());
        Assertions.assertEquals(usersToCheck.get(2).getName(), users.get(2).getName());
        Assertions.assertEquals(usersToCheck.size(), 3);

    }

    @Test
    void getRoleByName(){
        //given
        Role role = new Role("1","User_Role");
        //when
        lenient().when(roleDAL.getRoleByRoleName("User_Role")).thenReturn(role);
        //then
        RoleDTO roleDTO = userService.getRole("User_Role");

        Assertions.assertEquals(roleDTO.getRoleId(), roleDTO.getRoleId());
        Assertions.assertEquals(roleDTO.getRoleName(), roleDTO.getRoleName());
    }

    @Test
    void editUser_ReturnTrue(){
        //Given
        Role role = new Role("11", "Job_seeker");
        User user = new User("11", "11", "11","11","11","11", role);
        UserDTO userDTO = new UserDTO("11","test","test","test", "test", "test", "Job_seeker");
        //When
        when(userDAL.getUserByUserId("11")).thenReturn(user);
        boolean expected = userService.editUser(userDTO);
        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userDAL).editUser(userArgumentCaptor.capture());

        User captureUser = userArgumentCaptor.getValue();

        Assertions.assertEquals(userDTO.getEmail(), captureUser.getEmail());
        Assertions.assertEquals(userDTO.getName(), captureUser.getName());
        Assertions.assertEquals(userDTO.getAddress(), captureUser.getAddress());
        Assertions.assertEquals(userDTO.getRoleName(), captureUser.getRole().getRoleName());
        Assertions.assertTrue(expected);
    }

    @Test
    void deleteUser_ReturnTrue(){
        //given
        User user = new User("name","username","email","password","address",new Role("Role_User"));
        //When
        when(userDAL.getUserByUsername("username")).thenReturn(user);
        boolean expected = userService.deleteUser("username");
        //then
        Assertions.assertTrue(expected);
    }

    @Test
    void editPassword_ReturnTrue(){
        //given
        User user = new User("name","username","email","password","address",new Role("Role_User"));
        //When
        when(userDAL.getUserByUsername("username")).thenReturn(user);
        boolean expected = userService.editPassword("username","password");
        //then
        Assertions.assertTrue(expected);
    }

    @Test
    void updateRole_returnTrue(){
        Role role = new Role("1","User_Role");
        User user = new User("name","username","email","password","address",new Role("Role_User"));
        //When
        when(roleDAL.getRoleByRoleName("User_Role")).thenReturn(role);
        when(userDAL.getUserByUsername("username")).thenReturn(user);
        //then
        boolean expected = userService.updateUserRole("username", "User_Role");
        Assertions.assertTrue(expected);
    }
}
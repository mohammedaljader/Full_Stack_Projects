package org.project.joboffers.Controllers;


import org.project.joboffers.DTO.Response.MessageResponse;
import org.project.joboffers.DTO.UpdateUserRoleDTO;
import org.project.joboffers.DTO.UserDTO;
import org.project.joboffers.ServiceInterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username){
        UserDTO user = userService.getUserByUsername(username);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable String id){
        if(userService.deleteUser(id)){
            return ResponseEntity.ok().body(new MessageResponse("User deleted successfully!!"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Can not deleting the user, Please gives us a valid username!!"));
    }

    @PostMapping("/user/register")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody UserDTO userDTO){
        if(userService.addUser(userDTO)){
            return ResponseEntity.ok().body(new MessageResponse("You are registered successfully!!"));
        }
        else {
            return ResponseEntity.badRequest().body(new MessageResponse("Username or Email already exists!!"));
        }
    }

    @PutMapping("/user")
    public ResponseEntity<MessageResponse> editUser(@RequestBody UserDTO userDTO){
        if(userService.editUser(userDTO)){
            return ResponseEntity.ok().body(new MessageResponse("Your profile is edited successfully!!"));
        }
        else {
            return ResponseEntity.badRequest().body(new MessageResponse("Can not editing the user, Please gives us a valid username!!"));
        }
    }

    @PostMapping("/user/updateUserRole")
    public ResponseEntity<MessageResponse> updateUserRole(@RequestBody UpdateUserRoleDTO userRole){
        if(userService.updateUserRole(userRole.getUsername(), userRole.getRoleName())){
            return ResponseEntity.ok().body(new MessageResponse("The user is successfully upgraded !!"));
        }
        else {
            return ResponseEntity.badRequest().body(new MessageResponse("Can not upgrade this user, Please try again!!"));
        }
    }

    @PutMapping("/user/forgetPassword")
    public ResponseEntity<MessageResponse> forgetPassword(@RequestBody UserDTO userDTO){
        if(userService.editPassword(userDTO.getUsername(), userDTO.getPassword())){
            return ResponseEntity.ok().body(new MessageResponse("Your password is updated successfully!!"));
        }
        else {
            return ResponseEntity.badRequest().body(new MessageResponse("Can not editing the password, Please gives us a valid username!!"));
        }
    }

}

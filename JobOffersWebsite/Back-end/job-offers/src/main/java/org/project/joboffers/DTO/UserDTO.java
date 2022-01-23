package org.project.joboffers.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserDTO {
    private String userId;
    private String name;
    private String username;
    private String password;
    private String email;
    private String address;
    private String roleName;

    public UserDTO(String name, String username, String password, String email, String address, String roleName) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.roleName = roleName;
    }
}

package org.project.joboffers.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class RoleDTO {
    private String roleId;
    private String roleName;

    public RoleDTO(String roleName) {
        this.roleName = roleName;
    }
}

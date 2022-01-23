package org.project.joboffers.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Role implements Serializable {
    //region Properties
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false,length = 36)
    private String roleId;

    @Column(nullable = false, unique = true)
    private String roleName;
    //endregion

    //region Relations
    @OneToMany(mappedBy = "role",
            cascade= {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST}
    )
    @JsonIgnore
    private List<User> Users = new ArrayList<>();
    //endregion

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

}

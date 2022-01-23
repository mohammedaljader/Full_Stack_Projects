package org.project.joboffers.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// TODO: 11/16/2021 adding more columns like telephone number, data of birth and city
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class User implements Serializable {
    //region Properties
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false,length = 36)
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false , unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;
    //endregion

    //region Relations
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL, orphanRemoval = true
    )
    @JsonIgnore
    private List<Job> jobs = new ArrayList<>();

    @OneToMany(mappedBy = "senderUser",
            cascade= {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST}
    )
    @JsonIgnore
    private List<Message> senderMessages = new ArrayList<>();

    @OneToMany(mappedBy = "receiverUser",
            cascade= {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST}
    )
    @JsonIgnore
    private List<Message> receiverMessages = new ArrayList<>();

    @OneToMany(mappedBy = "user",
            cascade= {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST}
    )
    @JsonIgnore
    List<JobApplication> jobApplications = new ArrayList<>();

    @OneToMany(mappedBy = "user",
            cascade= {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST}
    )
    @JsonIgnore
    List<FavoriteJob> myJobs = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Role role;
    //endregion

    //region Constructors
    public User(String name, String username, String email, String password, String address, Role role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public User(String userId, String name, String username, String email, String password, String address, Role role) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    //endregion

}

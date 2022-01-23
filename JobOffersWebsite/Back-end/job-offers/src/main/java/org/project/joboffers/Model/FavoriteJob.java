package org.project.joboffers.Model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="favorite_jobs")
public class FavoriteJob implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false,length = 36)
    private String favoriteJobId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Job_id")
    private Job job;

    @Column(nullable = false)
    private LocalDateTime savedAt;

    public FavoriteJob(User user, Job job, LocalDateTime savedAt) {
        this.user = user;
        this.job = job;
        this.savedAt = savedAt;
    }


}

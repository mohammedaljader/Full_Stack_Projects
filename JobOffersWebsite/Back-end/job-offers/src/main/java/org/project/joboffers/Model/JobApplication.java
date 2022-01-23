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
@Table(name="job_applications")
public class JobApplication implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false,length = 36)
    private String jobApplyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Job_id")
    private Job job;

    @Column(nullable = false)
    private LocalDateTime appliedAt;

    @Column(nullable = false)
    private String cv;

    @Column(nullable = false)
    @Lob
    private byte[] cvData;

    @Column(nullable = false)
    private String motivation;

    public JobApplication(User user, Job job ,String motivation, LocalDateTime appliedAt) {
        this.user = user;
        this.job = job;
        this.motivation = motivation;
        this.appliedAt = appliedAt;
    }

    public JobApplication(String jobApplyId, User user, Job job, LocalDateTime appliedAt, String cv, String motivation) {
        this.jobApplyId = jobApplyId;
        this.user = user;
        this.job = job;
        this.appliedAt = appliedAt;
        this.cv = cv;
        this.motivation = motivation;
    }

    public JobApplication(User user, Job job, LocalDateTime appliedAt, String cv, byte[] cvData, String motivation) {
        this.user = user;
        this.job = job;
        this.appliedAt = appliedAt;
        this.cv = cv;
        this.cvData = cvData;
        this.motivation = motivation;
    }


}

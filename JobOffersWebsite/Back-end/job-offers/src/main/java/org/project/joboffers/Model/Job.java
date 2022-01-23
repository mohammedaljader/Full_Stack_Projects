package org.project.joboffers.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="jobs")
public class Job implements Serializable {

    //region Properties/ columns
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false,length = 36)
    private String jobId;

    @Column(name = "Job_name", nullable = false)
    private String jobName;

    @Column(name = "Job_Description", nullable = false)
    private String jobDescription;

    @Column(name = "Job_Salary", nullable = false)
    private double jobSalary;

    @Column(name = "Job_Address", nullable = false)
    private String jobAddress;

    @Column(name = "Job_image", nullable = false)
    private String imageName;

    @Column(name = "image_data", nullable = false)
    @Lob
    private byte[] imageData;
    //endregion

    //region Relations
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "User_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "job",
            cascade= {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JsonIgnore
    List<JobApplication> jobApplications;

    @OneToMany(mappedBy = "job",
            cascade= {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST })
    @JsonIgnore
    List<FavoriteJob> myJobs;
    //endregion

    //region Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return jobId.equals(job.jobId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId);
    }
    //endregion

    //region Constructors

    public Job(String jobName, String jobDescription, double jobSalary, String jobAddress, String imageName, byte[] imageData, Category category, User user) {
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.jobSalary = jobSalary;
        this.jobAddress = jobAddress;
        this.imageName = imageName;
        this.imageData = imageData;
        this.category = category;
        this.user = user;
    }

    public Job(String jobId, String jobName, String jobDescription, double jobSalary, String jobAddress, String imageName, byte[] imageData, Category category, User user) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.jobSalary = jobSalary;
        this.jobAddress = jobAddress;
        this.imageName = imageName;
        this.imageData = imageData;
        this.category = category;
        this.user = user;
    }

    public Job(String jobName, String jobDescription, double jobSalary, String jobAddress, Category category, User user) {
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.jobSalary = jobSalary;
        this.jobAddress = jobAddress;
        this.category = category;
        this.user = user;
    }

    public Job(String jobId, String jobName, String jobDescription, String jobAddress, double jobSalary, Category category , User user) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.jobSalary = jobSalary;
        this.jobAddress = jobAddress;
        this.category = category;
        this.user = user;
    }
    //endregion

}
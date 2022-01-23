package org.project.joboffers.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDTO {
    private String jobApplyId;
    private String cv;
    private String motivation;
    private LocalDateTime appliedAt;
    private String username;
    private String name;
    private String email;
    private String jobId;
    private String jobName;
    private String jobAddress;
    private double jobSalary;
    private String jobOwnerUsername;

    public JobApplicationDTO(String motivation,String username, String jobId) {
        this.motivation = motivation;
        this.username = username;
        this.jobId = jobId;
    }
}

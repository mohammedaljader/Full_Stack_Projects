package org.project.joboffers.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteJobDTO {
    private String favoriteJobId;
    private String jobId;
    private String jobName;
    private String jobDescription;
    private double jobSalary;
    private String jobImage;
    private String username;
    private LocalDateTime savedAt;
}

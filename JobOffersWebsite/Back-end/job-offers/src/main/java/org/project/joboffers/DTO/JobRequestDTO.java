package org.project.joboffers.DTO;

import org.springframework.web.multipart.MultipartFile;

public class JobRequestDTO {
    private String jobName;
    private String jobDescription;
    private float jobSalary;
    private String jobAddress;
    private String categoryId;
    private String username;


    public String getJobName() {
        return jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public float getJobSalary() {
        return jobSalary;
    }

    public String getJobAddress() {
        return jobAddress;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getUsername() {
        return username;
    }
}

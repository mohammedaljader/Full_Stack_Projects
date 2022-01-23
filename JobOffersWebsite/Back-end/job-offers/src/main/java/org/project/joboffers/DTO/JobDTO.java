package org.project.joboffers.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data @NoArgsConstructor @AllArgsConstructor
public class JobDTO {
    private String id;
    private String jobName;
    private String jobDescription;
    private double jobSalary;
    private String jobAddress;
    private String jobImage;
    private String categoryId; //id belongs to category
    private String username; //username belongs to the job owner


    public JobDTO(String jobName, String jobDescription, double jobSalary, String jobAddress,String jobImage,String categoryId, String username) {
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.jobSalary = jobSalary;
        this.jobAddress = jobAddress;
        this.jobImage = jobImage;
        this.categoryId = categoryId;
        this.username = username;
    }

    public JobDTO(String jobName, String jobDescription, double jobSalary, String jobAddress, String categoryId, String username) {
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.jobSalary = jobSalary;
        this.jobAddress = jobAddress;
        this.categoryId = categoryId;
        this.username = username;
    }
}
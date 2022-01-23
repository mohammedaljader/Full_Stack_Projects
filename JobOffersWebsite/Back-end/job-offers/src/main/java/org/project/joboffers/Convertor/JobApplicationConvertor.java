package org.project.joboffers.Convertor;


import org.project.joboffers.DTO.JobApplicationDTO;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.JobApplication;
import org.project.joboffers.Model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JobApplicationConvertor implements IJobApplicationConvertor{

    @Override
    public JobApplicationDTO convertToDTO(JobApplication jobApplication) {
        return new JobApplicationDTO(jobApplication.getJobApplyId(),
                jobApplication.getCv(),
                jobApplication.getMotivation(),
                jobApplication.getAppliedAt(),
                jobApplication.getUser().getUsername(),
                jobApplication.getUser().getName(),
                jobApplication.getUser().getEmail(),
                jobApplication.getJob().getJobId(),
                jobApplication.getJob().getJobName(),
                jobApplication.getJob().getJobAddress(),
                jobApplication.getJob().getJobSalary(),
                jobApplication.getJob().getUser().getUsername());
    }

    @Override
    public JobApplication convertToModel(JobApplicationDTO jobApplicationDTO, User user, Job job) {
           return new JobApplication(user, job, jobApplicationDTO.getMotivation(),
                   LocalDateTime.now());
    }

    @Override
    public List<JobApplicationDTO> convertListToDto(List<JobApplication> jobApplications) {
        List<JobApplicationDTO> jobApplicationDTOS = new ArrayList<>();
        for (JobApplication jobApplication: jobApplications) {
            JobApplicationDTO jobApplicationDTO = this.convertToDTO(jobApplication);
            jobApplicationDTOS.add(jobApplicationDTO);
        }
        return jobApplicationDTOS;
    }
}

package org.project.joboffers.Convertor;

import org.project.joboffers.DTO.JobApplicationDTO;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.JobApplication;
import org.project.joboffers.Model.User;

import java.util.List;

public interface IJobApplicationConvertor {
    JobApplicationDTO convertToDTO(JobApplication jobApplication);
    JobApplication convertToModel(JobApplicationDTO jobApplicationDTO, User user, Job job);
    List<JobApplicationDTO> convertListToDto(List<JobApplication> jobApplications);
}

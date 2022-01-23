package org.project.joboffers.Convertor;

import org.project.joboffers.DTO.JobDTO;
import org.project.joboffers.Model.Category;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.User;

import java.util.List;

public interface IJobConvertor {
    JobDTO convertToDTO(Job job);
    Job convertToModel(JobDTO jobDTO, Category category, User user);
    List<JobDTO> convertListToDto(List<Job> jobs);
}

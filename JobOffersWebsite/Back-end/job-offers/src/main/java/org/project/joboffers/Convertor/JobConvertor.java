package org.project.joboffers.Convertor;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.project.joboffers.DTO.JobDTO;
import org.project.joboffers.Model.Category;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class JobConvertor implements IJobConvertor {

    @Override
    public JobDTO convertToDTO(Job job){
        return new JobDTO(job.getJobId(),
                job.getJobName(),
                job.getJobDescription(),
                job.getJobSalary(),
                job.getJobAddress(),
                job.getImageName(),
                job.getCategory().getCategoryId(),
                job.getUser().getUsername());
    }

    @Override
    public Job convertToModel(JobDTO jobDTO, Category category, User user) {
        return new Job(jobDTO.getId(),jobDTO.getJobName(),
                jobDTO.getJobDescription(),
                jobDTO.getJobAddress(),
                jobDTO.getJobSalary(),
                category, user);
    }

    @Override
    public List<JobDTO> convertListToDto(List<Job> jobs){
        List<JobDTO> jobDTOS = new ArrayList<>();
        for (Job job: jobs) {
            JobDTO jobDTO = this.convertToDTO(job);
            jobDTOS.add(jobDTO);
        }
        return jobDTOS;
    }
}

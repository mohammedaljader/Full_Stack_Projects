package org.project.joboffers.ServiceInterfaces;


import org.project.joboffers.DTO.JobDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IJobService {
    List<JobDTO> getAllJobs();

    JobDTO getJobById(String id);

    boolean addJob(JobDTO jobDTO, MultipartFile file) throws IOException;

    boolean deleteJob(String id);

    boolean editJob(JobDTO jobDTO);

    List<JobDTO> getAllJobsByCategory(String categoryId);

    List<JobDTO> getAllJobsByUser(String username);

    List<JobDTO> filterJobs(String searchName);
}

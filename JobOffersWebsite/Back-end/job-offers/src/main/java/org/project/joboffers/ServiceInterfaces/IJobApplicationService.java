package org.project.joboffers.ServiceInterfaces;

import org.project.joboffers.DTO.JobApplicationDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IJobApplicationService {
    List<JobApplicationDTO> getAllJobApplicationsByUser(String username);
    boolean addApplication(JobApplicationDTO jobApplicationDTO, MultipartFile file) throws IOException;
    boolean deleteApplication(String id);
    JobApplicationDTO getJobApplicationById(String id);
    List<JobApplicationDTO> findAllCandidatesForJob(String jobId);
}

package org.project.joboffers.Service;

import org.project.joboffers.Convertor.IJobApplicationConvertor;
import org.project.joboffers.DALInterfaces.IJobApplicationDAL;
import org.project.joboffers.DALInterfaces.IJobDAL;
import org.project.joboffers.DALInterfaces.IUserDAL;
import org.project.joboffers.DTO.JobApplicationDTO;
import org.project.joboffers.EmailConfig.IEmailSenderService;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.JobApplication;
import org.project.joboffers.Model.User;
import org.project.joboffers.ServiceInterfaces.IJobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service @Transactional
public class JobApplicationService implements IJobApplicationService {
    private final IJobApplicationDAL jobApplicationDAL;
    private final IUserDAL userDAL;
    private final IJobDAL jobDAL;
    private final IJobApplicationConvertor jobApplicationConvertor;
    private final IEmailSenderService emailSenderService;

    @Autowired
    public JobApplicationService(IJobApplicationDAL jobApplicationDAL,
                                 IUserDAL userDAL,
                                 IJobApplicationConvertor jobApplicationConvertor,
                                 IJobDAL jobDAL,
                                 IEmailSenderService emailSenderService){
        this.jobApplicationDAL = jobApplicationDAL;
        this.userDAL = userDAL;
        this.jobApplicationConvertor = jobApplicationConvertor;
        this.jobDAL = jobDAL;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public List<JobApplicationDTO> getAllJobApplicationsByUser(String username) {
        User user = userDAL.getUserByUsername(username);
        List<JobApplication> jobApplications = jobApplicationDAL.getAllJobApplicationsByUser(user);
        if(jobApplications != null){
            return jobApplicationConvertor.convertListToDto(jobApplications);
        }
        return null;
    }

    @Override
    public boolean addApplication(JobApplicationDTO jobApplicationDTO, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        User user = userDAL.getUserByUsername(jobApplicationDTO.getUsername());
        Job job  = jobDAL.getJobById(jobApplicationDTO.getJobId());
        if(job != null && user != null && checkFileType(file)){
            JobApplication jobApplication = new JobApplication(user,
                    job,
                    LocalDateTime.now(),
                    fileName,
                    file.getBytes(),
                    jobApplicationDTO.getMotivation());
            // TODO: 12/25/2021 Send to user email
            emailSenderService.sendEmail("maljader18@gmail.com","Apply for "+ job.getJobName(),"Thank you for applying for "+ job.getJobName());
            jobApplicationDAL.addApplication(jobApplication);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteApplication(String id) {
        JobApplication jobApplication = jobApplicationDAL.getJobApplicationById(id);
        if(jobApplication != null){
            jobApplicationDAL.deleteApplication(jobApplication);
            return true;
        }
        return false;
    }

    @Override
    public JobApplicationDTO getJobApplicationById(String id) {
        JobApplication jobApplication = jobApplicationDAL.getJobApplicationById(id);
        if(jobApplication != null){
            return jobApplicationConvertor.convertToDTO(jobApplication);
        }
        return null;
    }

    @Override
    public List<JobApplicationDTO> findAllCandidatesForJob(String jobId) {
        Job job = jobDAL.getJobById(jobId);
        return jobApplicationConvertor.convertListToDto(job.getJobApplications());
    }

    private boolean checkFileType(MultipartFile file){
        return Objects.equals(file.getContentType(), "application/pdf")
                || Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                || Objects.equals(file.getContentType(), "application/msword");
    }
}

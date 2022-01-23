package org.project.joboffers.Service;

import lombok.extern.slf4j.Slf4j;
import org.project.joboffers.Convertor.IJobConvertor;
import org.project.joboffers.DALInterfaces.ICategoryDAL;
import org.project.joboffers.DALInterfaces.IJobDAL;
import org.project.joboffers.DALInterfaces.IUserDAL;
import org.project.joboffers.DTO.JobDTO;
import org.project.joboffers.Model.Category;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.User;
import org.project.joboffers.ServiceInterfaces.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service @Transactional @Slf4j
public class JobService implements IJobService {
    private final IJobDAL jobDAL;
    private final ICategoryDAL categoryDAL;
    private final IUserDAL userDAL;
    private final IJobConvertor jobConvertor;

    @Autowired
    public JobService(IJobDAL jobDAL,
                      ICategoryDAL categoryDAL,
                      IUserDAL userDAL,
                      IJobConvertor jobConvertor) {
        this.jobDAL = jobDAL;
        this.categoryDAL = categoryDAL;
        this.userDAL = userDAL;
        this.jobConvertor = jobConvertor;
    }

    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> jobs =  jobDAL.getAllJobs();
        return jobConvertor.convertListToDto(jobs);
    }

    @Override
    public JobDTO getJobById(String id){
        Job job = jobDAL.getJobById(id);
        if(job == null){
            return null;
        }
        return jobConvertor.convertToDTO(job);
    }

    @Override
    public boolean addJob(JobDTO jobDTO, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Category category = categoryDAL.getByCategoryName(jobDTO.getCategoryId());
        User user = userDAL.getUserByUsername(jobDTO.getUsername());
        if(user != null && category != null && checkFileType(file)){
            Job job = new Job(jobDTO.getJobName(),
                    jobDTO.getJobDescription(),
                    jobDTO.getJobSalary(),
                    jobDTO.getJobAddress(),
                    fileName,
                    file.getBytes(),
                    category, user);
            jobDAL.addJob(job);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteJob(String id){
        Job job = jobDAL.getJobById(id);
        if(job == null){
            return false;
        }
        jobDAL.deleteJob(job);
        return true;
    }

    @Override
    public boolean editJob(JobDTO jobDTO){
        Category category = categoryDAL.getByCategoryName(jobDTO.getCategoryId());
        User user = userDAL.getUserByUsername(jobDTO.getUsername());
        if(user != null && category != null) {
            Job job = new Job(jobDTO.getId(),
                    jobDTO.getJobName(),
                    jobDTO.getJobDescription(),
                    jobDTO.getJobAddress(),
                    jobDTO.getJobSalary(),
                    category, user);
            jobDAL.editJobWithoutChaningImage(job);
            return true;
        }
        return false;
    }

    @Override
    public List<JobDTO> getAllJobsByCategory(String categoryId) {
        Category category = categoryDAL.getCategoryById(categoryId);
        List<Job> jobs = jobDAL.getAllJobsByCategory(category);
        if(category != null && jobs != null){
            return jobConvertor.convertListToDto(jobs);
        }
        return null;
    }

    @Override
    public List<JobDTO> getAllJobsByUser(String username) {
        User user = userDAL.getUserByUsername(username);
        List<Job> jobs = jobDAL.getAllJobsByUser(user);
        if (user != null && jobs != null) {
            return jobConvertor.convertListToDto(jobs);
        }
        return null;
    }

    @Override
    public List<JobDTO> filterJobs(String searchName) {
        List<Job> jobs = jobDAL.filterJobByNameOrAddressOrCategory(searchName, searchName);
        if(jobs != null){
            return jobConvertor.convertListToDto(jobs);
        }
        return null;
    }

    private boolean checkFileType(MultipartFile file){
        return Objects.equals(file.getContentType(), MediaType.IMAGE_PNG_VALUE) || Objects.equals(file.getContentType(), "image/jpg")
                || Objects.equals(file.getContentType(), MediaType.IMAGE_JPEG_VALUE);
    }

}

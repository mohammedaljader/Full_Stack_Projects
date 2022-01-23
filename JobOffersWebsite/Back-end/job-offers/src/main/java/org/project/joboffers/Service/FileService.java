package org.project.joboffers.Service;

import org.project.joboffers.DALInterfaces.IJobApplicationDAL;
import org.project.joboffers.DALInterfaces.IJobDAL;
import org.project.joboffers.DTO.FileDTO;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.JobApplication;
import org.project.joboffers.ServiceInterfaces.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService implements IFileService {
    private final IJobDAL jobDAL;
    private final IJobApplicationDAL jobApplicationDAL;

    @Autowired
    public FileService(IJobDAL jobDAL, IJobApplicationDAL jobApplicationDAL) {
        this.jobDAL = jobDAL;
        this.jobApplicationDAL = jobApplicationDAL;
    }

    @Override
    public FileDTO getImageFileByJobId(String jobId) {
        Job job = jobDAL.getJobById(jobId);
        return new FileDTO(job.getImageName(), job.getImageData());
    }

    @Override
    public FileDTO getCVFileByJobApplicationId(String jobApplicationId) {
        JobApplication jobApplication = jobApplicationDAL.getJobApplicationById(jobApplicationId);
        return new FileDTO(jobApplication.getCv(), jobApplication.getCvData());
    }

}

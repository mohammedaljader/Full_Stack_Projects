package org.project.joboffers.DAL;

import org.project.joboffers.DALInterfaces.IJobApplicationDAL;
import org.project.joboffers.JPARepository.JobApplicationRepo;
import org.project.joboffers.Model.JobApplication;
import org.project.joboffers.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobApplicationDAL implements IJobApplicationDAL {
    private final JobApplicationRepo jobApplicationRepo;

    @Autowired
    public JobApplicationDAL(JobApplicationRepo jobApplicationRepo) {
        this.jobApplicationRepo = jobApplicationRepo;
    }

    @Override
    public List<JobApplication> getAllJobApplicationsByUser(User user) {
        return jobApplicationRepo.findAllByUser(user);
    }

    @Override
    public void addApplication(JobApplication jobApplication) {
        jobApplicationRepo.save(jobApplication);
    }

    @Override
    public void deleteApplication(JobApplication jobApplication) {
       jobApplicationRepo.delete(jobApplication);
    }

    @Override
    public JobApplication getJobApplicationById(String id) {
        return jobApplicationRepo.findByJobApplyId(id);
    }
}

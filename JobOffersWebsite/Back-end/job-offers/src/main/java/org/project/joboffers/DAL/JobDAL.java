package org.project.joboffers.DAL;

import org.project.joboffers.DALInterfaces.IJobDAL;
import org.project.joboffers.JPARepository.JobRepo;
import org.project.joboffers.Model.Category;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobDAL implements IJobDAL {
    private final JobRepo jobRepo;

    @Autowired
    public JobDAL(JobRepo jobRepo) {
        this.jobRepo = jobRepo;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }

    @Override
    public Job getJobById(String id) {
        return jobRepo.findByJobId(id);
    }

    @Override
    public Job addJob(Job job) {
       return jobRepo.save(job);
    }

    @Override
    public void deleteJob(Job job) {
        jobRepo.delete(job);
    }

    @Override
    public Job editJob(Job job) {
        return jobRepo.save(job);
    }

    @Override
    public void editJobWithoutChaningImage(Job job) {
        jobRepo.updateJob(job.getJobName(), job.getJobDescription(), job.getJobAddress(), job.getJobSalary(), job.getCategory(), job.getUser(), job.getJobId());
    }

    @Override
    public List<Job> filterJobByNameOrAddressOrCategory(String jobName, String jobAddress) {
        return jobRepo.findAllByJobNameOrJobAddress(jobName, jobAddress);
    }

    @Override
    public List<Job> getAllJobsByCategory(Category category) {
        return jobRepo.findAllByCategory(category);
    }

    @Override
    public List<Job> getAllJobsByUser(User user) {
        return jobRepo.findAllByUser(user);
    }
}

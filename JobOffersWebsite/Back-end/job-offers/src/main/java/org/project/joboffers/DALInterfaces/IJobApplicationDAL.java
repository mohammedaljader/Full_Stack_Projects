package org.project.joboffers.DALInterfaces;

import org.project.joboffers.Model.JobApplication;
import org.project.joboffers.Model.User;

import java.util.List;

public interface IJobApplicationDAL {
    List<JobApplication> getAllJobApplicationsByUser(User user);
    void addApplication(JobApplication jobApplication);
    void deleteApplication(JobApplication jobApplication);
    JobApplication getJobApplicationById(String id);
}

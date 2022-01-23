package org.project.joboffers.DALInterfaces;


import org.project.joboffers.Model.Category;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.User;

import java.util.List;

public interface IJobDAL {
    List<Job> getAllJobs();
    Job getJobById(String id);
    Job addJob(Job job);
    void deleteJob(Job job);
    Job editJob(Job job);
    List<Job> getAllJobsByCategory(Category category);
    List<Job> getAllJobsByUser(User user);
    void editJobWithoutChaningImage(Job job);
    List<Job> filterJobByNameOrAddressOrCategory(String jobName, String jobAddress);

}

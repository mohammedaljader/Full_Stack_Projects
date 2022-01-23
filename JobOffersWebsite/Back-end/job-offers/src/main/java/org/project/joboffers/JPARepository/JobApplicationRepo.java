package org.project.joboffers.JPARepository;

import org.project.joboffers.Model.JobApplication;
import org.project.joboffers.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepo extends JpaRepository<JobApplication, String> {
    JobApplication findByJobApplyId(String id);
    List<JobApplication> findAllByUser(User user);
}

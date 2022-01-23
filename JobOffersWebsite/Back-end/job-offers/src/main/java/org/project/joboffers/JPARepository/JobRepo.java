package org.project.joboffers.JPARepository;

import org.project.joboffers.Model.Category;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface JobRepo extends JpaRepository<Job, String> {
    Job findByJobId(String id);
    List<Job> findAllByCategory(Category category);
    List<Job> findAllByUser(User user);
    List<Job> findAllByJobNameOrJobAddress(String jobName, String jobAddress);

    @Modifying
    @Transactional
    @Query("Update Job j SET j.jobName=:name, j.jobDescription=:description, j.jobAddress=:address, j.jobSalary=:salary, j.category=:category, j.user=:user WHERE j.jobId =:jobId")
    void updateJob(@Param("name") String jobName,
                    @Param("description") String jobDescription,
                    @Param("address") String jobAddress,
                    @Param("salary") double jobSalary,
                   @Param("category") Category category,
                   @Param("user") User user,
                   @Param("jobId") String jobId);
}

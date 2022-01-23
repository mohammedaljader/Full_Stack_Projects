package org.project.joboffers.JPARepository;

import org.project.joboffers.Model.FavoriteJob;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteJobRepo extends JpaRepository<FavoriteJob, String> {
    FavoriteJob findByFavoriteJobId(String id);
    boolean existsByUserAndJob(User user, Job job);
}

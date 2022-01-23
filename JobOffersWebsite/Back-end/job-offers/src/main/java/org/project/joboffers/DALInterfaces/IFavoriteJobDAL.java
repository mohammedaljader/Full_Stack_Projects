package org.project.joboffers.DALInterfaces;

import org.project.joboffers.Model.FavoriteJob;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.User;

public interface IFavoriteJobDAL {
    void addJobToList(FavoriteJob favoriteJob);
    void deleteJobFromList(FavoriteJob favoriteJob);
    FavoriteJob getFavoriteJobById(String id);
    boolean existsByUserAndJob(User user, Job job);
}

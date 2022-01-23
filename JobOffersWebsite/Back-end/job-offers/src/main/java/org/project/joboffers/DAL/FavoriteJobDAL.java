package org.project.joboffers.DAL;

import org.project.joboffers.DALInterfaces.IFavoriteJobDAL;
import org.project.joboffers.JPARepository.FavoriteJobRepo;
import org.project.joboffers.Model.FavoriteJob;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FavoriteJobDAL implements IFavoriteJobDAL {
    private final FavoriteJobRepo favoriteJobRepo;

    @Autowired
    public FavoriteJobDAL(FavoriteJobRepo favoriteJobRepo){
        this.favoriteJobRepo = favoriteJobRepo;
    }

    @Override
    public void addJobToList(FavoriteJob favoriteJob) {
        favoriteJobRepo.save(favoriteJob);
    }

    @Override
    public void deleteJobFromList(FavoriteJob favoriteJob) {
         favoriteJobRepo.delete(favoriteJob);
    }

    @Override
    public FavoriteJob getFavoriteJobById(String id) {
        return favoriteJobRepo.findByFavoriteJobId(id);
    }

    @Override
    public boolean existsByUserAndJob(User user, Job job) {
        return favoriteJobRepo.existsByUserAndJob(user, job);
    }
}

package org.project.joboffers.Service;

import org.project.joboffers.Convertor.IFavoriteJobConvertor;
import org.project.joboffers.DALInterfaces.IFavoriteJobDAL;
import org.project.joboffers.DALInterfaces.IJobDAL;
import org.project.joboffers.DALInterfaces.IUserDAL;
import org.project.joboffers.DTO.FavoriteJobDTO;
import org.project.joboffers.Model.FavoriteJob;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.User;
import org.project.joboffers.ServiceInterfaces.IFavoriteJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavoriteJobService implements IFavoriteJobService {
    private final IUserDAL userDAL;
    private final IJobDAL jobDAL;
    private final IFavoriteJobDAL favoriteJobDAL;
    private final IFavoriteJobConvertor favoriteJobConvertor;

    @Autowired
    public FavoriteJobService(IUserDAL userDAL,
                              IJobDAL jobDAL,
                              IFavoriteJobDAL favoriteJobDAL,
                              IFavoriteJobConvertor favoriteJobConvertor) {
        this.userDAL = userDAL;
        this.jobDAL = jobDAL;
        this.favoriteJobDAL = favoriteJobDAL;
        this.favoriteJobConvertor = favoriteJobConvertor;
    }

    @Override
    public List<FavoriteJobDTO> getAllFavoriteJobsByUser(String username) {
        User user = userDAL.getUserByUsername(username);
        if(user != null){
            List<FavoriteJob> favoriteJobs = user.getMyJobs();
            return favoriteJobConvertor.convertListToDto(favoriteJobs);
        }
        return null;
    }

    @Override
    public boolean addJobToFavoriteList(String username, String jobId) {
        User user = userDAL.getUserByUsername(username);
        Job job = jobDAL.getJobById(jobId);
        boolean existsByUserAndJob = favoriteJobDAL.existsByUserAndJob(user, job);
        if(user != null && job != null && !existsByUserAndJob){
            FavoriteJob favoriteJob = new FavoriteJob(user, job, LocalDateTime.now());
            favoriteJobDAL.addJobToList(favoriteJob);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteJobToFavoriteList(String favoriteJobId) {
        FavoriteJob favoriteJob = favoriteJobDAL.getFavoriteJobById(favoriteJobId);
        if(favoriteJob != null){
            favoriteJobDAL.deleteJobFromList(favoriteJob);
            return true;
        }
        return false;
    }
}

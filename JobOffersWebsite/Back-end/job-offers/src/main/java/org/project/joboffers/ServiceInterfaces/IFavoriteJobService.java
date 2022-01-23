package org.project.joboffers.ServiceInterfaces;

import org.project.joboffers.DTO.FavoriteJobDTO;
import org.project.joboffers.Model.FavoriteJob;

import java.util.List;

public interface IFavoriteJobService {
    List<FavoriteJobDTO> getAllFavoriteJobsByUser(String username);
    boolean addJobToFavoriteList(String username, String jobId);
    boolean deleteJobToFavoriteList(String favoriteJobId);
}

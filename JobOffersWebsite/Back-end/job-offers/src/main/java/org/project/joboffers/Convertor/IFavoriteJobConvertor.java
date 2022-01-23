package org.project.joboffers.Convertor;


import org.project.joboffers.DTO.FavoriteJobDTO;
import org.project.joboffers.Model.FavoriteJob;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.User;

import java.util.List;

public interface IFavoriteJobConvertor {
    FavoriteJobDTO convertToDTO(FavoriteJob favoriteJob);
    List<FavoriteJobDTO> convertListToDto(List<FavoriteJob> favoriteJobs);
}

package org.project.joboffers.Convertor;

import org.project.joboffers.DTO.FavoriteJobDTO;
import org.project.joboffers.Model.FavoriteJob;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Component
public class FavoriteJobConvertor implements IFavoriteJobConvertor{
    @Override
    public FavoriteJobDTO convertToDTO(FavoriteJob favoriteJob) {
        return new FavoriteJobDTO(favoriteJob.getFavoriteJobId(),
                favoriteJob.getJob().getJobId(),
                favoriteJob.getJob().getJobName(),
                favoriteJob.getJob().getJobDescription(),
                favoriteJob.getJob().getJobSalary(),
                favoriteJob.getJob().getImageName(),
                favoriteJob.getUser().getUsername(),
                favoriteJob.getSavedAt());
    }

    @Override
    public List<FavoriteJobDTO> convertListToDto(List<FavoriteJob> favoriteJobs) {
        List<FavoriteJobDTO> favoriteJobDTOS = new ArrayList<>();
        for (FavoriteJob favoriteJob: favoriteJobs) {
             FavoriteJobDTO favoriteJobDTO = this.convertToDTO(favoriteJob);
             favoriteJobDTOS.add(favoriteJobDTO);
        }
        return favoriteJobDTOS;
    }
}

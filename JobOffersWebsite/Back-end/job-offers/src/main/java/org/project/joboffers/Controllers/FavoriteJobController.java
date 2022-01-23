package org.project.joboffers.Controllers;

import org.project.joboffers.DTO.FavoriteJobDTO;
import org.project.joboffers.DTO.Response.MessageResponse;
import org.project.joboffers.ServiceInterfaces.IFavoriteJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteJobController {
    private final IFavoriteJobService favoriteJobService;

    @Autowired
    public FavoriteJobController(IFavoriteJobService favoriteJobService){
        this.favoriteJobService = favoriteJobService;
    }

    @GetMapping("/favoriteJobs/{id}")
    public ResponseEntity<List<FavoriteJobDTO>> getFavoriteJobById(@PathVariable String id){
        List<FavoriteJobDTO> favoriteJobDTO = favoriteJobService.getAllFavoriteJobsByUser(id);
        return ResponseEntity.ok().body(getImagePath(favoriteJobDTO));
    }

    @PostMapping("/addJobToList")
    public ResponseEntity<MessageResponse> addJobToList(@RequestBody FavoriteJobDTO favoriteJobDTO){
        boolean result = favoriteJobService.addJobToFavoriteList(favoriteJobDTO.getUsername(), favoriteJobDTO.getJobId());
        if(result){
            return ResponseEntity.ok().body(new MessageResponse("Job added successfully!!"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Job already exists in the list!!"));
    }

    @DeleteMapping("/deleteJobFromList/{id}")
    public ResponseEntity<MessageResponse> deleteJob(@PathVariable String id) {
        if(favoriteJobService.deleteJobToFavoriteList(id)){
            return ResponseEntity.ok().body(new MessageResponse("Job deleted from successfully"));
        }
        else {
            return ResponseEntity.badRequest().body(new MessageResponse("OOPS! something went wrong, please try again!!"));
        }
    }

    private String getImagePath(String jobId){
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/files/")
                .path(jobId)
                .toUriString();
    }

    private List<FavoriteJobDTO> getImagePath(List<FavoriteJobDTO> favoriteJobDTOS){
        for (FavoriteJobDTO favoriteJobDTO: favoriteJobDTOS){
            favoriteJobDTO.setJobImage(getImagePath(favoriteJobDTO.getJobId()));
        }
        return favoriteJobDTOS;
    }
}

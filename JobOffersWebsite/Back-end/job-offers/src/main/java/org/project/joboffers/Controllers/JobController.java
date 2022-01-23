package org.project.joboffers.Controllers;


import lombok.extern.slf4j.Slf4j;
import org.project.joboffers.DTO.FileDTO;
import org.project.joboffers.DTO.JobDTO;
import org.project.joboffers.DTO.Response.MessageResponse;
import org.project.joboffers.ServiceInterfaces.IFileService;
import org.project.joboffers.ServiceInterfaces.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class JobController {
    private final IJobService jobService;
    private final IFileService fileService;

    @Autowired
    public JobController(IJobService jobService, IFileService fileService) {
        this.jobService = jobService;
        this.fileService = fileService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        List<JobDTO> jobs = jobService.getAllJobs();
        if(jobs != null) {
            return ResponseEntity.ok().body(getImagePath(jobs));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<List<JobDTO>> getAllJobsByUser(@PathVariable String id) {
        List<JobDTO> jobs = jobService.getAllJobsByUser(id);
        if(jobs != null) {
            return ResponseEntity.ok().body(getImagePath(jobs));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/jobs/category/{id}")
    public ResponseEntity<List<JobDTO>> getAllJobsByCategory(@PathVariable String id) {
        List<JobDTO> jobs = jobService.getAllJobsByCategory(id);
        if(jobs != null) {
            return ResponseEntity.ok().body(getImagePath(jobs));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/jobs/filter/{searchName}")
    public ResponseEntity<List<JobDTO>> filterJobs(@PathVariable String searchName) {
        List<JobDTO> jobs = jobService.filterJobs(searchName);
        if(jobs != null) {
            return ResponseEntity.ok().body(getImagePath(jobs));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable(value = "id") String id) {
        JobDTO jobDTO = jobService.getJobById(id);
        if(jobDTO != null) {
            jobDTO.setJobImage(getImagePath(jobDTO.getId())); //to get the path of the image
            return ResponseEntity.ok().body(jobDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/job/test")
    public ResponseEntity<MessageResponse> addJobWithoutImage(@RequestBody JobDTO jobDTO){
        try {
            boolean job = jobService.addJob(jobDTO, null);
            if(job) {
                return ResponseEntity.ok().body(new MessageResponse("Job added successfully!!"));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Can not add job, Please try again"));
            }
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/job")
    public ResponseEntity<MessageResponse> addJob(@RequestParam("file") MultipartFile file,
                                                  @RequestParam String jobName,
                                                  @RequestParam String jobDescription,
                                                  @RequestParam float jobSalary,
                                                  @RequestParam String jobAddress,
                                                  @RequestParam String categoryId,
                                                  @RequestParam String username){
        try {
            JobDTO jobDTO = new JobDTO(jobName, jobDescription, jobSalary, jobAddress, categoryId, username);
            boolean result = jobService.addJob(jobDTO,file);
            log.info(file.getContentType());
            if(result){
                return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Job added successfully!!"));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Can not add job, Please try again"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse("Could not upload the file: " + file.getOriginalFilename() + "!"));
        }
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<MessageResponse> deleteJob(@PathVariable String id) {
        JobDTO jobDTO = jobService.getJobById(id);
        if(jobDTO == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Please provide a valid job id."));
        }
        else
        {
            jobService.deleteJob(id);
            return ResponseEntity.ok().body(new MessageResponse("Job deleted successfully!"));
        }
    }

    @PutMapping("/job")
    public ResponseEntity<MessageResponse> updateJob(@RequestBody JobDTO updatedJob) {
        boolean job = jobService.editJob(updatedJob);
        if(job) {
            return ResponseEntity.ok().body(new MessageResponse("Job updated successfully!!"));
        } else {
            String entity =  "This job with the id " + updatedJob.getId() + " already exists.";
            return ResponseEntity.badRequest().body(new MessageResponse(entity));
        }
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String id) {
        FileDTO fileDTO = fileService.getImageFileByJobId(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDTO.getFileName() + "\"")
                .body(fileDTO.getFileData());
    }

    private String getImagePath(String jobId){
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/files/")
                .path(jobId)
                .toUriString();
    }

    private List<JobDTO> getImagePath(List<JobDTO> jobDTOS){
        for (JobDTO jobDTO: jobDTOS){
            jobDTO.setJobImage(getImagePath(jobDTO.getId()));
        }
        return jobDTOS;
    }

}

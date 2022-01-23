package org.project.joboffers.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.project.joboffers.DTO.FileDTO;
import org.project.joboffers.DTO.JobApplicationDTO;
import org.project.joboffers.DTO.Response.MessageResponse;
import org.project.joboffers.ServiceInterfaces.IFileService;
import org.project.joboffers.ServiceInterfaces.IJobApplicationService;
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
public class JobApplicationController {
    private final IJobApplicationService jobApplicationService;
    private final IFileService fileService;

    @Autowired
    public JobApplicationController(IJobApplicationService jobApplicationService, IFileService fileService){
        this.jobApplicationService = jobApplicationService;
        this.fileService = fileService;
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<List<JobApplicationDTO>> getJobApplicationsByUser(@PathVariable String id){
        List<JobApplicationDTO> jobApplications = jobApplicationService.getAllJobApplicationsByUser(id);
        return ResponseEntity.ok().body(getFilesPath(jobApplications));
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<JobApplicationDTO> getJobApplicationById(@PathVariable String id){
        JobApplicationDTO jobApplication = jobApplicationService.getJobApplicationById(id);
        jobApplication.setCv(getFilePath(id));
        return ResponseEntity.ok().body(jobApplication);
    }


    @PostMapping("/application")
    public ResponseEntity<MessageResponse> applyForJob(@RequestParam("file") MultipartFile file,
                                                       @RequestParam String motivation,
                                                       @RequestParam String jobId,
                                                       @RequestParam String username){
        try {
            JobApplicationDTO jobApplicationDTO = new JobApplicationDTO(motivation, username, jobId);
            boolean result = jobApplicationService.addApplication(jobApplicationDTO, file);
            if(result){
                return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("your job application sent successfully!!"));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Can not apply for this job, Please try again"));
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse("Could not upload the file: " + file.getOriginalFilename() + "!"));
        }
    }

    @GetMapping("/job/candidates/{id}")
    public ResponseEntity<List<JobApplicationDTO>> getAllCandidatesForJob(@PathVariable(value = "id") String id) {
        List<JobApplicationDTO> jobApplicationDTOS = jobApplicationService.findAllCandidatesForJob(id);
        if(jobApplicationDTOS != null) {
            return ResponseEntity.ok().body(getFilesPath(jobApplicationDTOS));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/application/{id}")
    public ResponseEntity<MessageResponse> deleteJobApplication(@PathVariable String id) {
        JobApplicationDTO jobApplicationDTO = jobApplicationService.getJobApplicationById(id);
        if(jobApplicationDTO != null){
            jobApplicationService.deleteApplication(id);
            return ResponseEntity.ok().body(new MessageResponse("Job Application is deleted successfully!!"));

        }
        else {
            return ResponseEntity.badRequest().body(new MessageResponse("Please provide a valid job id."));
        }
    }

    @GetMapping("/cv/files/{id}")
    public ResponseEntity<byte[]> downloadCV(@PathVariable String id) {
        FileDTO fileDTO = fileService.getCVFileByJobApplicationId(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDTO.getFileName() + "\"")
                .body(fileDTO.getFileData());
    }

    private String getFilePath(String jobApplicationId){
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/cv/files/")
                .path(jobApplicationId)
                .toUriString();
    }

    private List<JobApplicationDTO> getFilesPath(List<JobApplicationDTO> jobApplicationDTOS){
        for (JobApplicationDTO jobDTO: jobApplicationDTOS){
            jobDTO.setCv(getFilePath(jobDTO.getJobApplyId()));
        }
        return jobApplicationDTOS;
    }
}

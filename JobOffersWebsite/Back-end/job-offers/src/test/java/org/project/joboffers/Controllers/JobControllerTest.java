package org.project.joboffers.Controllers;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.project.joboffers.DTO.JobDTO;
import org.project.joboffers.Model.Category;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.User;
import org.project.joboffers.Security.SecurityConfig;
import org.project.joboffers.ServiceInterfaces.IFileService;
import org.project.joboffers.ServiceInterfaces.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobController.class)
@Disabled
class JobControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IJobService jobService;

//    @MockBean
//    private IFileService fileService;
//
//    @MockBean
//    private SecurityConfig securityConfig;

    
    @Test
    @Disabled
    void addJob() throws Exception {
        List<JobDTO> jobs = List.of(
                new JobDTO("", "", 1, "", "", ""),
                new JobDTO("", "", 1, "", "", ""),
                new JobDTO("", "", 1, "", "", ""),
                new JobDTO("", "", 1, "", "", ""),
                new JobDTO("", "", 1, "", "", "")
        );
        when(jobService.getAllJobs()).thenReturn(jobs);
        mockMvc.perform(get("/api/jobs"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
package org.project.joboffers.ServiceMockTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.joboffers.Convertor.JobApplicationConvertor;
import org.project.joboffers.DALInterfaces.IJobApplicationDAL;
import org.project.joboffers.DALInterfaces.IJobDAL;
import org.project.joboffers.DALInterfaces.IUserDAL;
import org.project.joboffers.DTO.JobApplicationDTO;
import org.project.joboffers.EmailConfig.IEmailSenderService;
import org.project.joboffers.Model.*;
import org.project.joboffers.Service.JobApplicationService;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobApplicationServiceTests {
    @Mock
    private IJobApplicationDAL jobApplicationDAL;
    @Mock
    private IUserDAL userDAL;
    @Mock
    private IJobDAL jobDAL;
    @Mock
    private IEmailSenderService emailSenderService;

    private JobApplicationService jobApplicationService;

    @BeforeEach
    void setUp() {
        jobApplicationService = new JobApplicationService(jobApplicationDAL, userDAL, new JobApplicationConvertor(), jobDAL,emailSenderService);

        User user = new User("11", "11", "11", "11", "11", "11", new Role());
        Job job = new Job("11","11","11",10, "11", "11", null,new Category(),new User());


        List<JobApplication> jobApplications = List.of(
                new JobApplication(user,job , LocalDateTime.now(), "11.pdf", null, ""),
                new JobApplication(user,job , LocalDateTime.now(), "11.doc", null, ""),
                new JobApplication(user,job , LocalDateTime.now(), "11.docs", null, "")
        );

        lenient().when(userDAL.getUserByUsername("11")).thenReturn(user);
        lenient().when(jobDAL.getJobById("11")).thenReturn(job);
        lenient().when(jobApplicationDAL.getAllJobApplicationsByUser(user)).thenReturn(jobApplications);
    }

    @Test
    void getAllJobApplicationsByUser_ReturnAllOfThem(){
        List<JobApplicationDTO> jobApplicationDTOS = jobApplicationService.getAllJobApplicationsByUser("11");
        Assertions.assertEquals(jobApplicationDTOS.get(0).getCv() , "11.pdf");
        Assertions.assertEquals(jobApplicationDTOS.get(1).getCv() , "11.doc");
        Assertions.assertEquals(jobApplicationDTOS.get(2).getCv() , "11.docs");
        Assertions.assertEquals(jobApplicationDTOS.size(), 3);
    }


    @Test
    void getAllJobApplicationsByUser_WithInvalidUsername_ReturnNull(){
        List<JobApplicationDTO> jobApplicationDTOS = jobApplicationService.getAllJobApplicationsByUser("1111");
        Assertions.assertEquals(jobApplicationDTOS.size(), 0);
    }

    @Test
    void Add_JobApplication_returnTrue() throws IOException {
        //Given
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "Hello, World!".getBytes()
        );
        //When
        JobApplicationDTO jobApplicationDTO = new JobApplicationDTO("11", "11", "11");
        boolean expected = jobApplicationService.addApplication(jobApplicationDTO, file);
        //then
        ArgumentCaptor<JobApplication> jobApplicationArgumentCaptor =
                ArgumentCaptor.forClass(JobApplication.class);
        verify(jobApplicationDAL)
                .addApplication(jobApplicationArgumentCaptor.capture());
        JobApplication jobApplicationArgumentCaptorValue = jobApplicationArgumentCaptor.getValue();

        Assertions.assertEquals(jobApplicationArgumentCaptorValue.getCv(), "hello.pdf");
        Assertions.assertEquals(jobApplicationArgumentCaptor.getValue().getUser().getEmail(),"11");
        Assertions.assertEquals(jobApplicationArgumentCaptor.getValue().getJob().getJobName(),"11");
        Assertions.assertEquals(jobApplicationArgumentCaptor.getValue().getMotivation(),"11");
        Assertions.assertEquals(jobApplicationArgumentCaptor.getValue().getUser().getAddress(),"11");
        Assertions.assertEquals(expected, true);
    }

    @Test
    void addJobApplication_WithNullFile(){
        JobApplicationDTO jobApplicationDTO = new JobApplicationDTO("11", "11", "11");
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class,
                () -> jobApplicationService.addApplication(jobApplicationDTO, null));

        Assertions.assertNull(thrown.getMessage());
    }

    @Test
    void addJobApplication_WithInvalid_CategoryId_And_Username() throws IOException {
        //Given
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "Hello, World!".getBytes()
        );
        //When
        JobApplicationDTO jobApplicationDTO = new JobApplicationDTO("11", "1111", "1111");
        boolean expected = jobApplicationService.addApplication(jobApplicationDTO, file);
        //then
        Assertions.assertFalse(expected);
    }

    @Test
    void deleteJobApplication_returnTrue(){
        //Given
        JobApplication jobApplication = new JobApplication("11", new User(), new Job(), LocalDateTime.now(), "11", "11");
        //When
        lenient().when(jobApplicationDAL.getJobApplicationById("11")).thenReturn(jobApplication);
        boolean expected = jobApplicationService.deleteApplication("11");
        //then
        verify(jobApplicationDAL).deleteApplication(jobApplication);
        Assertions.assertTrue(expected);
    }

    @Test
    void deleteJobApplication_WithInvalidId_returnFalse(){
        //When
        boolean expected = jobApplicationService.deleteApplication("1111");
        //then
        Assertions.assertFalse(expected);
    }

    @Test
    void getJobApplicationById_returnTheObject(){
        Job job = new Job("", "", 10, "", new Category(),new User());
        JobApplication jobApplication = new JobApplication("11", new User(), job, LocalDateTime.now(), "cv", "mot");
        //When
        lenient().when(jobApplicationDAL.getJobApplicationById("11")).thenReturn(jobApplication);
        JobApplicationDTO jobApplicationDTO = jobApplicationService.getJobApplicationById("11");
        //then
        Assertions.assertEquals(jobApplicationDTO.getCv(), "cv");
        Assertions.assertEquals(jobApplicationDTO.getMotivation(), "mot");
    }

    @Test
    void getJobApplicationById_WithInvalidId_returnNothing(){
        //When
        JobApplicationDTO jobApplicationDTO = jobApplicationService.getJobApplicationById("11111");
        //then
        Assertions.assertEquals(jobApplicationDTO, null);
    }

}

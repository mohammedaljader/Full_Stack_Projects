package org.project.joboffers.ServiceMockTests;

import org.project.joboffers.Convertor.JobConvertor;
import org.project.joboffers.DALInterfaces.ICategoryDAL;
import org.project.joboffers.DALInterfaces.IJobDAL;
import org.project.joboffers.DALInterfaces.IUserDAL;
import org.project.joboffers.DTO.JobDTO;
import org.project.joboffers.Model.Category;
import org.project.joboffers.Model.Job;
import org.project.joboffers.Model.Role;
import org.project.joboffers.Model.User;
import org.project.joboffers.Service.JobService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class jobServiceTests {

    @Mock
    private IJobDAL jobDAL;
    @Mock
    private ICategoryDAL categoryDAL;
    @Mock
    private IUserDAL userDAL;


    private JobService jobService;

    @BeforeEach
    void setUp(){
        jobService = new JobService(jobDAL, categoryDAL, userDAL, new JobConvertor());
        Category category = new Category("1","test");
        User user = new User("test", "testUser", "test", "test" ,"test",new Role());

        List<Job> jobs = List.of(
                new Job("1","test", "test", 100.00,"test", "",null , category, user),
                new Job("2","test1", "test1",200.99,"test1","",null , new Category(), user),
                new Job("3","test2", "test2",300.00,"test2","",null, category, new User()),
                new Job("4","test3", "test3",400.00, "test3","",null, new Category(), user),
                new Job("5","test4", "test4", 300.91, "test4","",null, category, user)
        );
        List<Job> selectedJobsByCategory = new ArrayList<>();
        for (Job job: jobs) {
            if(job.getCategory() == category){
                selectedJobsByCategory.add(job);
            }
        }

        List<Job> selectedJobsByUser = new ArrayList<>();
        for (Job job: jobs) {
            if(job.getUser() == user){
                selectedJobsByUser.add(job);
            }
        }

        Job job = new Job("6","test6", "test6", "test6",100.00,  new Category(), new User());
        lenient().when(jobDAL.getJobById("6")).thenReturn(job);
        lenient().when(jobDAL.getAllJobs()).thenReturn(jobs);
        lenient().when(categoryDAL.getCategoryById("1")).thenReturn(category);
        lenient().when(jobDAL.getAllJobsByCategory(category)).thenReturn(selectedJobsByCategory);
        lenient().when(userDAL.getUserByUsername("testUser")).thenReturn(user);
        lenient().when(jobDAL.getAllJobsByUser(user)).thenReturn(selectedJobsByUser);
    }

    @Test
    void getJobList() {
        List<JobDTO> jobDTOS = jobService.getAllJobs();
        Assertions.assertEquals(jobDTOS.get(0).getJobName() , "test");
        Assertions.assertEquals(jobDTOS.get(1).getJobName() , "test1");
        Assertions.assertEquals(jobDTOS.get(2).getJobName() , "test2");
        Assertions.assertEquals(jobDTOS.get(3).getJobName() , "test3");
        Assertions.assertEquals(jobDTOS.get(4).getJobName() , "test4");
        Assertions.assertEquals(jobDTOS.size(), 5);
    }

    @Test
    void getJob_WithCorrectId_ReturnCorrectJob() {
        JobDTO job = jobService.getJobById("6");
        Assertions.assertEquals(job.getJobName() , "test6");
        Assertions.assertEquals(job.getId(), "6");
        Assertions.assertEquals(job.getJobAddress(), "test6");
        Assertions.assertEquals(job.getJobDescription(), "test6");
        Assertions.assertEquals(job.getJobSalary(), 100.00);
    }

    @Test
    void getJob_WithUnCorrectId_ReturnUnCorrectJob() {
        //when
        when(jobDAL.getJobById("7")).thenReturn(null);
        //then
        JobDTO job = jobService.getJobById("7");
        Assertions.assertNull(job);
    }

    @Test
    void addJob() throws IOException {
        //Given
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.png",
                MediaType.IMAGE_PNG_VALUE,
                "Hello, World!".getBytes()
        );
        //When
        when(categoryDAL.getByCategoryName("11")).thenReturn(new Category());
        when(userDAL.getUserByUsername("11")).thenReturn(new User());

        JobDTO jobDTO = new JobDTO("1", "Test", "description", 10.99, "address","hello.png", "11", "11");
        jobService.addJob(jobDTO, file);
        //then
        ArgumentCaptor<Job> jobArgumentCaptor =
                ArgumentCaptor.forClass(Job.class);
        verify(jobDAL)
                .addJob(jobArgumentCaptor.capture());
        Job jobArgumentCaptorValue = jobArgumentCaptor.getValue();

        Assertions.assertEquals(jobArgumentCaptorValue.getJobName(), "Test");
        Assertions.assertEquals(jobArgumentCaptor.getValue().getImageName(),"hello.png");
        Assertions.assertEquals(jobArgumentCaptor.getValue().getJobSalary(),10.99);
        Assertions.assertEquals(jobArgumentCaptor.getValue().getJobDescription(),"description");
        Assertions.assertEquals(jobArgumentCaptor.getValue().getJobAddress(),"address");
    }

    @Test
    void addJob_WithNullValuesOfCategoryAndUser() throws IOException {
        //Given
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        //When
        when(categoryDAL.getByCategoryName(null)).thenReturn(null);
        when(userDAL.getUserByUsername(null)).thenReturn(null);

        JobDTO jobDTO = new JobDTO("1", "Test", "description", 10.99, "address","hello.txt", null, null);
        boolean expected = jobService.addJob(jobDTO, file);
        //then
        Assertions.assertFalse(expected);
    }

    @Test
    void addJob_WithNullFile(){
        JobDTO jobDTO = new JobDTO("1", "Test", "description", 10.99, "address","hello.txt", null, null);
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class,
                () -> jobService.addJob(jobDTO, null));

        Assertions.assertNull(thrown.getMessage());
    }

    @Test
    void deleteJob() {
        //Given
        Job job = new Job("1","Test", "", "",10.99,new Category(), new User());
        //When
        lenient().when(jobDAL.getJobById("1")).thenReturn(job);
        jobService.deleteJob("1");
        //then
        verify(jobDAL).deleteJob(job);
    }

    @Test
    void deleteJob_WithInvalidId_returnNull() {
        //When
        lenient().when(jobDAL.getJobById("1000")).thenReturn(null);
        boolean expected = jobService.deleteJob("1");
        //then
        Assertions.assertFalse(expected);
    }

    @Test
    void editJob_withCorrectId() {
        //Given
        Job job = new Job("1","Test", "", "",10.99, new Category(), new User());
        //When
        when(categoryDAL.getByCategoryName(null)).thenReturn(new Category());
        when(userDAL.getUserByUsername(null)).thenReturn(new User());
        lenient().when(jobDAL.getJobById("1")).thenReturn(job);
        JobDTO jobDTO = new JobDTO("1", "Test", "description", 10.99, "address",null, null, null);
        jobService.editJob(jobDTO);
        //then
        ArgumentCaptor<Job> jobArgumentCaptor =
                ArgumentCaptor.forClass(Job.class);
        verify(jobDAL)
                .editJobWithoutChaningImage(jobArgumentCaptor.capture());
        Job jobArgumentCaptorValue = jobArgumentCaptor.getValue();

        Assertions.assertEquals(jobArgumentCaptorValue.getJobName(), "Test");
        Assertions.assertEquals(jobArgumentCaptorValue.getJobSalary(), 10.99);
        Assertions.assertEquals(jobArgumentCaptorValue.getJobAddress(), "address");
        Assertions.assertEquals(jobArgumentCaptorValue.getJobDescription(), "description");
    }

    @Test
    void editJob_withInvalidId(){
        lenient().when(jobDAL.getJobById("1000")).thenReturn(null);
        JobDTO jobDTO = new JobDTO("1000", "Test", "", 10.99,"", null, null, null);
        boolean expected = jobService.editJob(jobDTO);
        //then
        Assertions.assertFalse(expected);
    }

    @Test
    void findAllJobsByCategory_WithCorrectCategoryId_returnListOfJobs() {
        //then
        List<JobDTO> jobs = jobService.getAllJobsByCategory("1");
        Assertions.assertEquals(jobs.get(0).getJobName(), "test");
        Assertions.assertEquals(jobs.get(1).getJobName(), "test2");
        Assertions.assertEquals(jobs.get(2).getJobName(), "test4");
        Assertions.assertEquals(jobs.size(), 3);
    }

    @Test
    void findAllJobsByCategory_WithUnCorrectCategoryId_returnNull() {
        //when
        lenient().when(jobDAL.getAllJobsByCategory(new Category())).thenReturn(null);
        List<JobDTO> jobs = jobService.getAllJobsByCategory("10");
        Assertions.assertNull(jobs);
    }

    @Test
    void getAllJobsByUser() {
        List<JobDTO> jobs = jobService.getAllJobsByUser("testUser");
        Assertions.assertEquals(jobs.get(0).getJobName(), "test");
        Assertions.assertEquals(jobs.get(1).getJobName(), "test1");
        Assertions.assertEquals(jobs.get(2).getJobName(), "test3");
        Assertions.assertEquals(jobs.get(3).getJobName(), "test4");
        Assertions.assertEquals(jobs.size(), 4);

    }

    @Test
    void getAllJobsByUser_WithInvalidUsername() {
        //when
        lenient().when(jobDAL.getAllJobsByUser(new User())).thenReturn(null);
        List<JobDTO> jobs = jobService.getAllJobsByUser("test");
        Assertions.assertNull(jobs);

    }
}
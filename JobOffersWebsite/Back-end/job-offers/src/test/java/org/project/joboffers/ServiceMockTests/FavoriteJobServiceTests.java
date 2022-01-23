package org.project.joboffers.ServiceMockTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.joboffers.Convertor.FavoriteJobConvertor;
import org.project.joboffers.DALInterfaces.IFavoriteJobDAL;
import org.project.joboffers.DALInterfaces.IJobDAL;
import org.project.joboffers.DALInterfaces.IUserDAL;
import org.project.joboffers.DTO.FavoriteJobDTO;
import org.project.joboffers.Model.*;
import org.project.joboffers.Service.FavoriteJobService;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class FavoriteJobServiceTests {

    @Mock
    private IUserDAL userDAL;
    @Mock
    private IJobDAL jobDAL;
    @Mock
    private IFavoriteJobDAL favoriteJobDAL;

    private FavoriteJobService favoriteJobService;

    @BeforeEach
    void setUp() {
        favoriteJobService = new FavoriteJobService(userDAL, jobDAL, favoriteJobDAL, new FavoriteJobConvertor());

        User user = new User("11", "11", "11", "11", "11", "11", new Role());
        Job job = new Job("11","11","11",10, "11", "11", null,new Category(),new User());

        List<FavoriteJob> favoriteJobList = List.of(
                new FavoriteJob(user,job, LocalDateTime.now()),
                new FavoriteJob(user,job, LocalDateTime.now()),
                new FavoriteJob(user,job, LocalDateTime.now()),
                new FavoriteJob(user,job, LocalDateTime.now())
        );
        user.setMyJobs(favoriteJobList);
        lenient().when(userDAL.getUserByUsername("11")).thenReturn(user);
        lenient().when(jobDAL.getJobById("11")).thenReturn(job);

    }

    @Test
    void getALlFavoriteList(){
        List<FavoriteJobDTO> favoriteJobDTOS = favoriteJobService.getAllFavoriteJobsByUser("11");
        Assertions.assertEquals(favoriteJobDTOS.get(0).getJobDescription() , "11");
        Assertions.assertEquals(favoriteJobDTOS.get(1).getJobDescription() , "11");
        Assertions.assertEquals(favoriteJobDTOS.get(2).getJobDescription() , "11");
        Assertions.assertEquals(favoriteJobDTOS.size(), 4);
    }

    @Test
    void getALlFavoriteList_WithInvalidUsername_ReturnNull(){
        List<FavoriteJobDTO> favoriteJobDTOS = favoriteJobService.getAllFavoriteJobsByUser("invalid");
        Assertions.assertNull(favoriteJobDTOS);
    }

    @Test
    void addJobToFavoriteList_WithCorrectUsernameAndJobId_returnTrue(){
        boolean expected = favoriteJobService.addJobToFavoriteList("11", "11");
        Assertions.assertTrue(expected);
    }

    @Test
    void addJobToFavoriteList_WithInvalidUsernameAndJobId_returnFalse(){
        boolean expected = favoriteJobService.addJobToFavoriteList("invalid", "invalid");
        Assertions.assertFalse(expected);
    }

    @Test
    void deleteJobFromFavoriteList_withCorrectId_ReturnTrue(){
        //given
        FavoriteJob favoriteJob =  new FavoriteJob(new User(),new Job(), LocalDateTime.now());
        favoriteJob.setFavoriteJobId("11");
        //when
        lenient().when(favoriteJobDAL.getFavoriteJobById("11")).thenReturn(favoriteJob);
        //then
        boolean expected = favoriteJobService.deleteJobToFavoriteList("11");
        Assertions.assertTrue(expected);

    }

    @Test
    void deleteJobFromFavoriteList_withInvalidId_ReturnFalse(){
        //then
        boolean expected = favoriteJobService.deleteJobToFavoriteList("invalid");
        Assertions.assertFalse(expected);

    }
}

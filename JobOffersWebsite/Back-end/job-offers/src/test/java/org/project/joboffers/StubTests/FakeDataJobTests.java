package org.project.joboffers.StubTests;

import org.project.joboffers.Model.Category;
import org.project.joboffers.Model.Job;
import org.project.joboffers.DAL.StubDAL.FakeJobDAL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FakeDataJobTests {
    @Test
     void GetAllJobs_returnAllJobTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        List<Job> actual = new ArrayList<>();
        Category FullTime = new Category("1L", "FullTime");
        Category PartTime = new Category("2L", "PartTime");
        Category Temporary = new Category("3L", "Temporary");
        Category SideJob = new Category("4L", "Side Job");
        actual.add(new Job("1L", "Software Developer", "Coding and testing the applications", "Eindhoven" , 1000.00, SideJob, null));
        actual.add(new Job("2L", "Teacher", "Teaching English", "Amsterdam" , 1300.00, PartTime, null));
        actual.add(new Job("3L", "Business manager", "managing the company", "DenHaag" , 2000.00, Temporary, null));
        actual.add(new Job("4L", "IT engineer", "Coding and testing", "Delft" , 2100.00, FullTime, null));
        actual.add(new Job("5L", "Civil engineer ", "build", "Nijmegen" , 2100.00, FullTime, null));
        //act
        List<Job> expected = fakeJobDAL.findAllJobs();
        //assert
        Assertions.assertNotNull(expected);
        Assertions.assertEquals(expected.size(), 5);
        Assertions.assertEquals(expected.get(0).getJobName(), actual.get(0).getJobName());
        Assertions.assertEquals(expected.get(1).getJobName(), actual.get(1).getJobName());
        Assertions.assertEquals(expected.get(2).getJobName(), actual.get(2).getJobName());
        Assertions.assertEquals(expected.get(3).getJobName(), actual.get(3).getJobName());
        Assertions.assertEquals(expected.get(4).getJobName(), actual.get(4).getJobName());
    }

    @Test
     void GetJob_returnTheCorrectJobTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        Category SideJob = new Category("4", "Side Job");
        Job actual = new Job("1", "Software Developer", "Coding and testing the applications", "Eindhoven" , 1000.00, SideJob, null);
        //act
        Job expected = fakeJobDAL.getJobsById("1");
        //assert
        Assertions.assertNotNull(expected);
        Assertions.assertEquals(expected.getJobName(),actual.getJobName());
        Assertions.assertEquals(expected.getJobDescription(),actual.getJobDescription());
        Assertions.assertEquals(expected.getJobAddress(),actual.getJobAddress());
        Assertions.assertEquals(expected.getJobSalary(),actual.getJobSalary());
        Assertions.assertEquals(expected.getCategory().getCategoryName(),actual.getCategory().getCategoryName());
    }

    @Test
     void GetJob_returnNullObjectTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        //act
        Job expected = fakeJobDAL.getJobsById("100L");
        //assert
        Assertions.assertNull(expected);
    }

    @Test
     void AddJob_returnTrueTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        Category category = fakeJobDAL.getCategoryById("1L");
        Job job = new Job("10L", "Test", "Test", "Test", 1000.00,category, null);
        //act
        boolean expected = fakeJobDAL.addJob(job);
        Job checkJob = fakeJobDAL.getJobsById("10L"); // I will check of this job is successfully added to the list
        //assert
        String actualTest = "Test";
        double actualSalary = 1000.00;
        Assertions.assertTrue(expected);
        Assertions.assertEquals(job.getJobName(), actualTest);
        Assertions.assertEquals(job.getJobDescription(), actualTest);
        Assertions.assertEquals(job.getJobAddress(), actualTest);
        Assertions.assertEquals(job.getJobSalary(), actualSalary);
        Assertions.assertNotNull(checkJob);
    }

    @Test
     void AddJob_returnFalseTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        Category category = new Category("11", "");
        Job job = new Job("1", "Test", "Test", "test", 1000.00,category, null);
        //act
        boolean expected = fakeJobDAL.addJob(job);
        Job checkJob = fakeJobDAL.getJobsById("1"); // I will test that the list didn't change
        //assert
        Assertions.assertFalse(expected);
        Assertions.assertNotEquals(checkJob.getJobName(), job.getJobName()); // so I have to compare these two objects in order to know that the list didn't change.
        Assertions.assertNotEquals(checkJob.getJobDescription() , job.getJobDescription());
    }

    @Test
     void DeleteJob_returnTrueTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        //act
        boolean expected = fakeJobDAL.removeJob("2");
        Job checkJob = fakeJobDAL.getJobsById("2"); //I will check if this object is deleted successfully
        //assert
        Assertions.assertTrue(expected);
        Assertions.assertNull(checkJob); //If this object is null, then I know that this object is deleted
    }

    @Test
     void DeleteJob_returnFalseTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        //act
        boolean expected = fakeJobDAL.removeJob("1000");
        Job checkJob= fakeJobDAL.getJobsById("1000"); // Check if this object already exist
        //assert
        Assertions.assertNull(checkJob); // this object is null if it doesn't exist
        Assertions.assertFalse(expected);
    }

    @Test
     void EditJob_returnTrueTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        Category category = fakeJobDAL.getCategoryById("2");
        Job job = new Job("1" , "", "", "" , 1222.00, category, null);
        //act
        boolean expected = fakeJobDAL.editJob(job);
        Job checkJob = fakeJobDAL.getJobsById("1"); //Check if this object is updated successfully
        //assert
        Assertions.assertTrue(expected);
        Assertions.assertEquals(job.getJobName(), checkJob.getJobName()); //The first object has to be equal to the other object
        Assertions.assertEquals(job.getCategory(), checkJob.getCategory());
        Assertions.assertEquals(job.getJobDescription(), checkJob.getJobDescription());
    }

    @Test
     void EditJob_returnFalseTest(){
        //arrange
        FakeJobDAL fakeJobDAL = new FakeJobDAL();
        Category category = fakeJobDAL.getCategoryById("2");
        Job job = new Job("1000" , "", "", "" , 1222.00, category, null);
        //act
        boolean expected = fakeJobDAL.editJob(job);
        Job checkJob = fakeJobDAL.getJobsById("1000"); //Check this id if exist in the data store
        //assert
        Assertions.assertFalse(expected);
        Assertions.assertNull(checkJob); // this id not exist in the data store, so it is a null object
    }
}

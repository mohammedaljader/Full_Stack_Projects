package org.project.joboffers.DAL.StubDAL;

import org.project.joboffers.Model.Category;
import org.project.joboffers.Model.Job;

import java.util.ArrayList;
import java.util.List;


public class FakeJobDAL {

    private final List<Category> categories = new ArrayList<>();
    private final List<Job> jobs = new ArrayList<>();

    public FakeJobDAL(){
        // Hardcode because we don't have database yet
        //categories instance and add to list
        Category fullTime = new Category("1", "FullTime" );
        Category partTime = new Category("2", "PartTime");
        Category temporary = new Category("3", "Temporary");
        Category side_job = new Category("4", "Side Job" );
        categories.add(fullTime);
        categories.add(partTime);
        categories.add(temporary);
        categories.add(side_job);

        //Jobs instance and then add to list
        jobs.add(new Job("1", "Software Developer", "Coding and testing the applications", "Eindhoven", 1000.00 , side_job, null));
        jobs.add(new Job("2", "Teacher", "Teaching English", "Amsterdam" , 1300.00, partTime, null));
        jobs.add(new Job("3", "Business manager", "managing the company", "DenHaag" , 2000.00, temporary, null));
        jobs.add(new Job("4", "IT engineer", "Coding and testing", "Delft" , 2100.00, fullTime, null));
        jobs.add(new Job("5", "Civil engineer ", "build", "Nijmegen" , 2100.00, fullTime, null));
    }


    public List<Category> findAllCategories() {
        return categories;
    }

    public Category getCategoryById(String id) {
        for (Category category: categories) {
            if(category.getCategoryId().equals(id)){
                return category;
            }
        }
        return null;
    }

    public boolean addCategory(Category category) {
        if(this.getCategoryById(category.getCategoryId()) == null){
            categories.add(category);
            return true;
        }
        return false;
    }

    public boolean removeCategory(String id) {
        Category category = this.getCategoryById(id);
        if(category != null){

            categories.remove(category);
            return true;
        }
        return false;
    }

    public boolean editCategory(Category category) {
        if(this.getCategoryById(category.getCategoryId()) != null){
            Category oldCategory = this.getCategoryById(category.getCategoryId());
            oldCategory.setCategoryName(category.getCategoryName());
            return true;
        }
        return false;
    }

    public List<Job> findAllJobs() {
        return jobs;
    }

    public Job getJobsById(String id) {
        for (Job job: jobs) {
            if(job.getJobId().equals(id)){
                return job;
            }
        }
        return null;
    }

    public boolean addJob(Job job) {
        if(this.getCategoryById(job.getJobId()) == null){
            jobs.add(job);
            return true;
        }
      return false;
    }

    public boolean removeJob(String id) {
        Job job = this.getJobsById(id);
        if(job != null){
            jobs.remove(job);
            return true;
        }
        return false;
    }

    public boolean editJob(Job job) {
        if(this.getCategoryById(job.getJobId()) != null){
            Job oldJob = this.getJobsById(job.getJobId());
            oldJob.setJobName(job.getJobName());
            oldJob.setJobDescription(job.getJobDescription());
            oldJob.setJobAddress(job.getJobAddress());
            oldJob.setCategory(job.getCategory());
            oldJob.setJobSalary(job.getJobSalary());
            return true;
        }
        return false;
    }
}

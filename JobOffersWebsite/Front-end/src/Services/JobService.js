import axios from 'axios';
import authHeader from './authHeader';

const Job_API_Base_URL = "http://localhost:8080/api/";

class JobService {

    getJobs(){
        return axios.get(Job_API_Base_URL + 'jobs', {headers: authHeader()});
    }

    getJobsByUser(username){
        return axios.get(Job_API_Base_URL + 'jobs/' + username, {headers: authHeader()});
    }

    getJobsByCategory(categoryId){
        return axios.get(Job_API_Base_URL + 'jobs/category/' + categoryId, {headers: authHeader()});
    }

    filterJobs(filterSearch){
        return axios.get(Job_API_Base_URL + 'jobs/filter/' + filterSearch, {headers: authHeader()});
    }

    createJob(job){
        return axios.post(Job_API_Base_URL + 'job', job , {headers: authHeader()});
    }

    getJobById(JobId){
        return axios.get(Job_API_Base_URL + 'job/' + JobId, {headers: authHeader()});
    }

    updateJob(job ){
        return axios.put(Job_API_Base_URL + 'job' , job , {headers: authHeader()});
    }

    deleteJob(JobId){
        return axios.delete(Job_API_Base_URL + 'job/' + JobId, {headers: authHeader()});
    }
}

export default new JobService()
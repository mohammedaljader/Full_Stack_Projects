import axios from 'axios';
import authHeader from './authHeader';

const Job_API_Base_URL = "http://localhost:8080/api/application";
const AllCandidates_API_Base_URL = "http://localhost:8080/api/job/candidates/";

class JobApplicationService {
    applyForJob(jobApplication){
        return axios.post(Job_API_Base_URL, jobApplication, {headers: authHeader()});
    }

    getAllJobApplicationByUser(username){
        return axios.get(Job_API_Base_URL+"s/" +username,{headers: authHeader()});
    }

    deleteJobApplication(applicationId){
        return axios.delete(Job_API_Base_URL+"/"+applicationId,{headers: authHeader()});
    }

    getAllCandidatesForJob(jobId){
        return axios.get(AllCandidates_API_Base_URL + jobId, {headers: authHeader()})
    }
}

export default new JobApplicationService();
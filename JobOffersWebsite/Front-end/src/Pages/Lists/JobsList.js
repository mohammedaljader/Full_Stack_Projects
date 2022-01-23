import React, {useState, useEffect} from 'react';
import JobList from '../../Components/JobComponents/JobList';
import JobService from '../../Services/JobService';

function JobsList() {
    const [jobs, setJobs] = useState([]);

    let activeUser = JSON.parse(localStorage.getItem('user'));

  useEffect(() => {
    JobService.getJobsByUser(activeUser.username).then((response) => {
      console.log(response.data);
      setJobs(response.data);
    });
  }, [activeUser.username]);// Runs ONCE after initial rendering
 

  function DeleteJob(id){
    const newJobs = [...jobs];
    const index = newJobs.indexOf(id);
    newJobs.splice(index, 1);
    JobService.deleteJob(id);
    setJobs(newJobs);
  }

  if(Object.keys(jobs).length === 0) return <h1 id="nojobs">No Jobs available right now!</h1>

    return (
      <section>
        <h1>All Jobs</h1>
        <JobList jobs={jobs} onDelete={DeleteJob} viewButton={true} />
      </section>  
    )
}

export default JobsList

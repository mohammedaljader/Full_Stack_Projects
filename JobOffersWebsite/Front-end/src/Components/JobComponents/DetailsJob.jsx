import React, {useState, useEffect} from 'react';
import { useHistory } from 'react-router-dom';
import JobService from '../../Services/JobService';
import JobItem from './JobItem';
import classes from './JobList.module.css'
import NotFound from '../UI/NotFound'

function DetailsJob() {
    const [job, setJob] = useState(null);
    const history = useHistory();
    let param = window.location.pathname;  //to find the path of id /viewjob/1
    let id = param.split('/').pop();

    // var id = url.substring(url.lastIndexOf('/') + 1);

    useEffect(() => {
      JobService.getJobById(id).then((response) => {
        console.log(response.data);
        setJob(response.data);
      });
    }, [id]);

    function DeleteJob(id)
    {
      JobService.deleteJob(id).then(() => {
        history.replace('/');
      });
    }
  
    if(!job) return <NotFound/> ;
  
      return (
        <ul className={classes.list}>
          <JobItem key={job.id}  job = {job} onDelete={DeleteJob} viewbutton={false} />
      </ul>
      )
}

export default DetailsJob

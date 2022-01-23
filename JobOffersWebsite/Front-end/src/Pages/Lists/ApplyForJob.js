import React, {useRef, useState} from 'react'
import Card from '../../Components/UI/Card'
import classes from '../../Components/JobComponents/NewJobForm.module.css';
import JobApplicationService from '../../Services/JobApplicationService';
import {Divider,CardContent} from '@mui/material';
import { useHistory } from 'react-router';
import  {useParams} from "react-router-dom";
import { useAlert } from 'react-alert';
import  JobViewApplication  from '../../Components/JobApplicationsComponents/JobViewApplication';



function ApplyForJob(props) {

    const [file, setFile] = useState(null);
    const alert = useAlert()
    const history = useHistory();
    let { id } = useParams();
    const motivation = useRef();

   
    let user = JSON.parse(localStorage.getItem('user'));
    const onChangeHandler=event=>{
      console.log(event.target.files[0])
      setFile(event.target.files[0])
    }

    function submitHandler(event) {
        event.preventDefault();
        const enteredMotivation = motivation.current.value;

      const JobApplicationDTO = {
        motivation: enteredMotivation,
        username: user.username,
        jobId: id
      }

      console.log(JobApplicationDTO)
      var data = new FormData();
      data.append('file', file);
      data.append('motivation', enteredMotivation);
      data.append('jobId', id);
      data.append('username', user.username);
      JobApplicationService.applyForJob(data).then(() => {
        alert.success('Your job application is sent successfully!', {timeout: 3000})
        history.replace('/');
      }).catch(err=>{console.log(err);
        alert.error('Can not apply for this job, please upload pdf/msword file!')
      });
    }


    return (
        <Card>
           <CardContent>
             <JobViewApplication job={props.job}/>
             <Divider />
              <form className={classes.form} onSubmit={submitHandler} >
                  <div className={classes.control}>
                    <label htmlFor='motivation'>Why do you want to work in this position?</label>
                    <input type='text' placeholder="Your answer.." required  ref={motivation} />
                  </div>
                  <div className={classes.control}>
                    <label htmlFor='jobImage'>CV</label>
                    <input type="file" name="file" accept=".doc,.docx, application/pdf" onChange={onChangeHandler} required />
                  </div>  
                  <div className={classes.actions}>
                    <button>Apply</button>
                  </div>
              </form>
        </CardContent>
      </Card>
    )
}

export default ApplyForJob

import React, {useEffect, useState} from 'react';
import { useHistory } from 'react-router-dom';
import NewJobForm from '../../Components/JobComponents/NewJobForm';
import CategoryService from '../../Services/CategoryService';
import JobService from '../../Services/JobService'
import { useAlert } from 'react-alert'

function AddJob() {
  const history = useHistory();
  const [Categories, setCategories] = useState([]);
  const alert = useAlert()
  
  useEffect(() => {
    CategoryService.getAllCategories().then((response) => {
      console.log(response.data);
      setCategories(response.data);
    });
  }, []);


  function addJobHandler(job) {
    JobService.createJob(job).then(() => {
      alert.success('The job added successfully!', {timeout: 3000})
      history.replace('/');
    }).catch(err=>{console.log(err);
      alert.error('Can not make a job, Please upload only image file!!')
    });
  }

    return (
      <div>
        <h1>Add job</h1>
        <NewJobForm Categories={Categories} onAddJob={addJobHandler}/>
      </div>
    )
}
export default AddJob

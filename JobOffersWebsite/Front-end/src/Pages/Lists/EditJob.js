import React, {useEffect, useState} from 'react';
import { useHistory } from 'react-router-dom';
import EditJobForm from '../../Components/JobComponents/EditJobForm'
import CategoryService from '../../Services/CategoryService'
import JobService from '../../Services/JobService'
import  {useParams} from "react-router-dom";
import NotFound from '../../Components/UI/NotFound';

function EditJob() {

    let { id } = useParams();
    const history = useHistory();
    const [Categories, setCategories] = useState([]);
    const [job, setJob] = useState([]);
    const [load, setLoad] = React.useState(false);

    useEffect(() => {
        CategoryService.getAllCategories().then((response) => {
          console.log(response.data);
          setCategories(response.data);
        });

        JobService.getJobById(id).then((response) => {
          console.log(response.data);
          setJob(response.data);
          setLoad(true)
        });
        setLoad(true)
      }, [id]);

      function EditJobHandler(Updatedjob) {
        JobService.updateJob(Updatedjob).then(() => {
          history.replace('/');
        });
      }  

    
    if(load === true && Object.keys(job).length === 0) return <NotFound/>

    return (
        <div >
            <h1>Edit Job</h1>
            <EditJobForm  job={job} Categories={Categories} onUpdatejob={EditJobHandler}/>
        </div>
    )
}

export default EditJob

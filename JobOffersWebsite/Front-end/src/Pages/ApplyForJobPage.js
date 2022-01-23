import React from 'react'
import AuthService from '../Services/AuthService';
import NotAllowed from '../Components/UI/NotAllowed';
import LoginComponent from '../Components/AuthComponents/LoginComponent';
import  {useParams} from "react-router-dom";
import NotFound from '../Components/UI/NotFound';
import ApplyForJob from './Lists/ApplyForJob';
import JobService from '../Services/JobService';

function ApplyForJobPage() {
    let { id } = useParams();
    const [job, setJob] = React.useState([]);
    const [load, setLoad] = React.useState(false)

    React.useEffect(() => {
        JobService.getJobById(id).then((response) => {
        console.log(response.data);
        setJob(response.data);
        setLoad(true)
      });
      setLoad(true)
    }, [id]);
    
    if(AuthService.IsAuthenticated() === false){
        return <LoginComponent />
    }

    if(AuthService.IsJobSeeker() === false && AuthService.IsAdmin() === false){
        return <NotAllowed />
    }

    if(Object.keys(job).length === 0 && load === true){
        return <NotFound/>
    }

    return (
        <ApplyForJob job={job}/>
    )
}

export default ApplyForJobPage

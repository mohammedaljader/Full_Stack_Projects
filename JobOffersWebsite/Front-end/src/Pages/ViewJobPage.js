import React from 'react'
import AuthService from '../Services/AuthService';
import LoginComponent from '../Components/AuthComponents/LoginComponent';
import  {useParams} from "react-router-dom";
import NotFound from '../Components/UI/NotFound';
import ViewJob from '../Components/JobComponents/ViewJob';

function ViewJobPage() {

    let { id } = useParams();
    
    if(AuthService.IsAuthenticated() === false){
        return <LoginComponent />
    }

    if(!id){
        return <NotFound/>
    }

    return (
        <>
        <h1>Job Details</h1>
        <br/>
        <ViewJob />
        </>
    )
}

export default ViewJobPage

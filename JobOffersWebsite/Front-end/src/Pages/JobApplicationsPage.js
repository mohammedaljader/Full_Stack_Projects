import React from 'react'
import AuthService from '../Services/AuthService';
import NotAllowed from '../Components/UI/NotAllowed';
import JobApplicationList from '../Components/JobApplicationsComponents/JobApplicationList';
import LoginComponent from '../Components/AuthComponents/LoginComponent';

function JobApplicationsPage() {

    if(AuthService.IsAuthenticated() === false){
        return <LoginComponent />
    }

    if(AuthService.IsJobSeeker() === false && AuthService.IsAdmin() === false){
        return <NotAllowed />
    }

    return (
        <>
        <h1>My Job applications</h1>
        <br/>
        <JobApplicationList />
        </>
    )    
}

export default JobApplicationsPage

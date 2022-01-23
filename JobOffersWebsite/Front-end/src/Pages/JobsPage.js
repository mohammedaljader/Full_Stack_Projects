import React from 'react'
import NotAllowed from '../Components/UI/NotAllowed';
import LoginComponent from '../Components/AuthComponents/LoginComponent';
import AuthService from '../Services/AuthService';
import JobsList from './Lists/JobsList';

function JobsPage() {
    if(AuthService.IsAuthenticated() === false){
        return <LoginComponent />
    }

    if(AuthService.IsJobPublisher() === false && AuthService.IsAdmin() === false){
        return <NotAllowed />
    }

    return (
        <JobsList />
    )
}

export default JobsPage

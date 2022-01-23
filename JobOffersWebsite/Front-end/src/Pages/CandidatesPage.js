import React from 'react';
import Candidates from '../Components/JobApplicationsComponents/Candidates';
import AuthService from '../Services/AuthService';
import NotAllowed from '../Components/UI/NotAllowed';
import LoginComponent from '../Components/AuthComponents/LoginComponent';

function CandidatesPage() {

    if(AuthService.IsAuthenticated() === false){
        return <LoginComponent />
    }

    if(AuthService.IsJobPublisher() === false && AuthService.IsAdmin() === false){
        return <NotAllowed />
    }

    return (
        <Candidates/>
    )
}

export default CandidatesPage



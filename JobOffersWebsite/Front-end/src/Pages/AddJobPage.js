import React from 'react'
import AuthService from '../Services/AuthService';
import NotAllowed from '../Components/UI/NotAllowed';
import LoginComponent from '../Components/AuthComponents/LoginComponent';
import AddJob from './Lists/AddJob';

function AddJobPage() {

    if(AuthService.IsAuthenticated() === false){
        return <LoginComponent />
    }

    if(AuthService.IsJobPublisher() === false && AuthService.IsAdmin() === false){
        return <NotAllowed />
    }

    return (
        <AddJob />
    )
}

export default AddJobPage

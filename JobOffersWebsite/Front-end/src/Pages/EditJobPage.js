import React from 'react'
import EditJob from './Lists/EditJob'
import AuthService from '../Services/AuthService';
import NotAllowed from '../Components/UI/NotAllowed';
import LoginComponent from '../Components/AuthComponents/LoginComponent';
import  {useParams} from "react-router-dom";
import NotFound from '../Components/UI/NotFound';

function EditJobPage() {
    let { id } = useParams();
    
    if(AuthService.IsAuthenticated() === false){
        return <LoginComponent />
    }

    if(AuthService.IsJobPublisher() === false && AuthService.IsAdmin() === false){
        return <NotAllowed />
    }

    if(!id){
        return <NotFound/>
    }

    return (
       <EditJob/>
    )
}

export default EditJobPage

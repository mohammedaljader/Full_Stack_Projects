import React from 'react'
import AuthService from '../Services/AuthService';
import NotAllowed from '../Components/UI/NotAllowed';
import LoginComponent from '../Components/AuthComponents/LoginComponent';
import UsersList from './Lists/UsersList';

function UsersPage() {

    if(AuthService.IsAuthenticated() === false){
        return <LoginComponent />
    }

    if(AuthService.IsAdmin() === false){
        return <NotAllowed />
    }

    return (
        <UsersList/>
    )
}

export default UsersPage

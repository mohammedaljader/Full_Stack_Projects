import React from 'react'
import AuthService from '../Services/AuthService';
import LoginComponent from '../Components/AuthComponents/LoginComponent';
import Account from './Lists/Account';

function AccountPage() {

    if(AuthService.IsAuthenticated() === false){
        return <LoginComponent />
    }

    return (
        <Account />
    )
}

export default AccountPage

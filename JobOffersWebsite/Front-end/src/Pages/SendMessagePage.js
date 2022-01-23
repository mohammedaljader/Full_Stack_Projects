import React from 'react'
import LoginComponent from '../Components/AuthComponents/LoginComponent';
import SendMessage from '../Components/MessagesComponents/SendMessage';
import AuthService from '../Services/AuthService';

function SendMessagePage() {

    if(AuthService.IsAuthenticated() === false){
        return <LoginComponent />
    }

    return (
        <SendMessage/>
    )
}

export default SendMessagePage

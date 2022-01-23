import React from 'react'
import AuthService from '../Services/AuthService';
import LoginComponent from '../Components/AuthComponents/LoginComponent';
import TestChat from '../Components/ChatComponents/TestChat';

function ChatPage() {

    if(AuthService.IsAuthenticated() === false){
        return <LoginComponent />
    }

    return (
        <TestChat />
    )
}

export default ChatPage

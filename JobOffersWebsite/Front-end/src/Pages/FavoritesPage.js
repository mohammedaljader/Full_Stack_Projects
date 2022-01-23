import React from 'react'
import FavoriteList from '../Components/JobComponents/FavoriteList';
import AuthService from '../Services/AuthService';
import NotAllowed from '../Components/UI/NotAllowed';
import LoginComponent from '../Components/AuthComponents/LoginComponent';

function FavoritesPage() {
   

    if(AuthService.IsAuthenticated() === false){
        return <LoginComponent />
    }

    if(AuthService.IsJobSeeker() === false && AuthService.IsAdmin() === false){
        return <NotAllowed />
    }

    return (
        <>
        <h1>My Favorite Jobs</h1>
        <br/>
        <FavoriteList/>
        </>
        
    )
}

export default FavoritesPage

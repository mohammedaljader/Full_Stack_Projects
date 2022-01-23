import React from 'react'
import AuthService from '../Services/AuthService';
import NotAllowed from '../Components/UI/NotAllowed';
import LoginComponent from '../Components/AuthComponents/LoginComponent';
import Categories from './Lists/Categories'

function CategoriesPage() {

    if(AuthService.IsAuthenticated() === false){
        return <LoginComponent />
    }

    if(AuthService.IsAdmin() === false){
        return <NotAllowed />
    }

    return (
        <Categories />
    )
}

export default CategoriesPage

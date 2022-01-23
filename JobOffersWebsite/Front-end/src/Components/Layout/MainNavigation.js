import React, { useState } from "react";
// import { NavLink } from "react-router-dom";
import './NavBar.css';
import AuthService from "../../Services/AuthService";
import NavbarMain from "./NavbarMain";
import NavbarAdmin from "./NavbarAdmin";
import NavbarUser from "./NavbarUser";
import { useHistory } from 'react-router-dom';
import NavbarJobPublisher from "./NavbarJobPublisher";

function MainNavigation() {
  const [click, setClick] = useState(false);
  const handleClick = () => setClick(!click);
  let user = JSON.parse(localStorage.getItem('user'));
  let isJobPublisher = false;
  let isAdmin = false;
  const history = useHistory();

  
  if(user && user.roleName.includes("Job_publisher")){
    isJobPublisher = true;
  }
  if(user && user.roleName.includes("Admin")){
    isAdmin = true;
  }

  function LogOut(){
     setClick(!click);
     AuthService.logout();
     history.replace('/');
     window.location.reload(true);
  }

  if(!user) return <NavbarMain click={click} handleClick={handleClick} />

  if(isAdmin === true){
    return <NavbarAdmin click={click} handleClick={handleClick} LogOut={LogOut} />
  }
  
  if(isJobPublisher === true){
    return <NavbarJobPublisher click={click} handleClick={handleClick} LogOut={LogOut} />
  }
  
  if(isJobPublisher === false && isAdmin === false)
  return (
    <NavbarUser click={click} handleClick={handleClick} LogOut={LogOut}/>
  );
}

export default MainNavigation;

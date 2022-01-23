import React from 'react'
import { NavLink } from "react-router-dom";
import './NavBar.css';

function NavbarJobPublisher(props) {
    return (
    <>
        <nav className="navbar">
            <div className="nav-container">
            <NavLink exact to="/" className="nav-logo">
                JobOffers
            </NavLink>
                <ul className={props.click ? "nav-menu active" : "nav-menu"}>
                    <li className="nav-item">
                    <NavLink
                    exact
                    to="/"
                    activeClassName="active"
                    className="nav-links"
                    onClick={props.handleClick}
                    >
                    Home
                    </NavLink>
                </li>
                <li className="nav-item">
                    <NavLink
                    exact
                    to="/addjob"
                    activeClassName="active"
                    className="nav-links"
                    onClick={props.handleClick}
                    >
                    AddJob
                </NavLink>
                </li>
                <li className="nav-item">
                <NavLink
                    exact
                    to="/myjobs"
                    activeClassName="active"
                    className="nav-links"
                    onClick={props.handleClick}
                >
                    MyJobs
                </NavLink>
                </li>
                <li className="nav-item">
                    <NavLink
                    exact
                    to="/chat"
                    activeClassName="active"
                    className="nav-links"
                    onClick={props.handleClick}
                    >
                    Chat
                    </NavLink>
                </li>
                <li className="nav-item login">
                    <NavLink
                    exact
                    to="/profile"
                    activeClassName="active"
                    className="nav-links"
                    >    
                    profile
                    </NavLink>    
                                        
                </li>
                <li className="nav-item login">
                    <NavLink
                    exact
                    to=""
                    activeClassName="active"
                    className="nav-links"
                    onClick={props.LogOut}
                    >    
                    log out
                    </NavLink>                            
                </li>
                </ul>
            <div className="nav-icon" onClick={props.handleClick}>
                <i className={props.click ? "fas fa-times" : "fas fa-bars"}></i>
            </div>   
            </div>
        </nav>
    </>
  );
}

export default NavbarJobPublisher

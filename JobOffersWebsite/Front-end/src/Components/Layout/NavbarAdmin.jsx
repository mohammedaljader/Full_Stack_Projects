import React from 'react'
import { NavLink } from "react-router-dom";
import './NavBar.css';

function NavbarAdmin(props) {
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
                    to="/categories"
                    activeClassName="active"
                    className="nav-links"
                    onClick={props.handleClick}
                >
                    Categories
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
                <li className="nav-item">
                    <NavLink
                    exact
                    to="/users"
                    activeClassName="active"
                    className="nav-links"
                    onClick={props.handleClick}
                    >
                    All users
                </NavLink>
                </li>
                <li className="nav-item login">
                    <NavLink
                    exact
                    to="/profile"
                    activeClassName="active"
                    className="nav-links"
                    >    
                    Profile
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

export default NavbarAdmin

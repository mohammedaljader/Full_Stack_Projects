import React from 'react'
import { NavLink } from "react-router-dom";
import './NavBar.css';

function NavbarMain(props) {
    return (
        <>
        <nav className="navbar">
          <div className="nav-container">
            <NavLink exact to="/" className="nav-logo">
              JobOffers
            </NavLink>
            <ul className={props.click ? "nav-menu active" : "nav-menu"}>
            <li className="nav-item login">
              <NavLink
                exact
                to="/register"
                activeClassName="active"
                className="nav-links"
                onClick={props.handleClick}
              >    
               Sign up 
              </NavLink>                          
            </li>
             <li className="nav-item login" >
              <NavLink
                  exact
                  to="/login"
                  activeClassName="active"
                  className="nav-links"
                  onClick={props.handleClick}>
                  Sign in
                </NavLink>            
              </li>
          </ul>
          <div className="nav-icon" onClick={props.handleClick}>
            <i className={props.click ? "fas fa-times" : "fas fa-bars"}></i>
          </div>   
        </div>
      </nav>
    </>
    )
}

export default NavbarMain

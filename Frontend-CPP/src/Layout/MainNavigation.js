import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import './NavBar.css';
import Filter from '../Components/Filters/Filter';

function MainNavigation() {
  const [click, setClick] = useState(false);
  const handleClick = () => setClick(!click);

  const [isOpen, setIsOpen] = useState(false);
 
  const togglePopup = () => {
    setClick(!click)
    setIsOpen(!isOpen);
  }

  const closeHandler = (Isopen) => {
    setIsOpen(Isopen);
  }

  return (
      <nav className="navbar">
        <div className="nav-container">
          <NavLink exact to="/" className="nav-logo">
           CPP Project
          </NavLink>
              <ul className={click ? "nav-menu active" : "nav-menu"}>
                <li className="nav-item">
                <NavLink
                  exact
                  to="/"
                  activeClassName="active"
                  className="nav-links"
                  onClick={handleClick}
                >
                  Home
                </NavLink>
              </li>
              <li className="nav-item">
                <NavLink
                  exact
                  to="/"
                  activeClassName="active"
                  className="nav-links"
                  onClick={togglePopup}
                >
                Filters
              </NavLink>
            </li>
            </ul>
          <div className="nav-icon" onClick={handleClick}>
            <i className={click ? "fas fa-times" : "fas fa-bars"}></i>
          </div>   
          {isOpen && <Filter
            handleClose={togglePopup}
            closeHandler={closeHandler}
            />}
        </div>
      </nav>
  );
}

export default MainNavigation;

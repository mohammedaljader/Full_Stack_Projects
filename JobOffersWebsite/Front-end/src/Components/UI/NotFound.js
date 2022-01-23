import React from 'react';
import { Link } from 'react-router-dom';
import "./NotFound.css"

function NotFound() {
    return (
        <div>
            <h1>404 page not found</h1>
            <p>We are sorry but the page you are looking for does not exist.</p>
            <h1 className="btn" style={{background: "#cc2062"}}><Link to='/' className="link">Go Home</Link></h1>
        </div>
    )
}

export default NotFound

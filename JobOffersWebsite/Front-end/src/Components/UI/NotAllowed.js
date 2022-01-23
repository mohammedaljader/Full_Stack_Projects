import React from 'react'
import { Link } from 'react-router-dom';
import "./NotFound.css"

function NotAllowed() {
    return (
        <div>
            <h1 className='notAllowed'>403</h1>
            <p><b>Sorry, it's not allowed to go beyond this point!</b></p>
            <h1 className="btn" style={{ background: "#cc2062" }}><Link to='/' className="link">Go Home</Link></h1>
        </div>
    )
}

export default NotAllowed

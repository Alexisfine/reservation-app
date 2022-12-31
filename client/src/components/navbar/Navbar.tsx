import React from 'react'
import { Link } from 'react-router-dom'
import './Navbar.scss'
const Navbar = () => {

  return (
    <div className="navbar">
        <div className="container">
          <Link to="/" style={{color:"inherit", textDecoration: "none"}}><span className="logo">Alex Booking</span></Link>
            <div className="items">
                <button className="button">Register</button>
                <button className="button">Login</button>
            </div>
        </div>
    </div>
  )
}

export default Navbar
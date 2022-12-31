import React, { useContext } from 'react'
import { Link } from 'react-router-dom'
import { AuthContext } from '../../context/authContext';
import './Navbar.scss'
const Navbar = () => {
  const {state} = useContext(AuthContext);
  const user = state.user;

  console.log(user);

  return (
    <div className="navbar">
        <div className="container">
          <Link to="/" style={{color:"inherit", textDecoration: "none"}}><span className="logo">Alex Booking</span></Link>
            {user ? user.username : <div className="items">
                <button className="button">Register</button>
                <button className="button">Login</button>
            </div>}
        </div>
    </div>
  )
}

export default Navbar
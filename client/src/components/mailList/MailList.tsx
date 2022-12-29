import React from 'react'
import './MailList.scss'

const MailList = () => {
  return (
    <div className="mail">
        <h1 className="title">Save time, save money!</h1>
        <span className="desc">Sign up and we'll send the best deals to you</span>
        <div className="container">
            <input type="text" placeholder='Your email'/>
            <button>Subscribe</button>
        </div>
    </div>
  )
}

export default MailList
import React, { useState } from 'react'
import "./Header.scss"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome"
import {faBed, faPlane, faCar, faTaxi, faCalendarDays, faPerson} from '@fortawesome/free-solid-svg-icons'
import {DateRange} from 'react-date-range';
import 'react-date-range/dist/styles.css';
import 'react-date-range/dist/theme/default.css';
import { addDays } from 'date-fns/esm';

interface DateTime {
    startDate?: Date | undefined,
    endDate?: Date | undefined,
    key?: string | undefined
}
const Header = () => {
    const [date, setDate] = useState<DateTime>(
        {
        startDate: new Date(),
        endDate: addDays(new Date(), 5),
        key: 'selection'
        }
    )

  return (
    <div className="header">
        <div className="container">
        <div className="list">
            <div className="item active">
                <FontAwesomeIcon icon={faBed}/>
                <span>Stays</span>
            </div>
            <div className="item">
                <FontAwesomeIcon icon={faPlane}/>
                <span>Flights</span>
            </div>
            <div className="item">
                <FontAwesomeIcon icon={faCar}/>
                <span>Car rentals</span>
            </div>
            <div className="item">
                <FontAwesomeIcon icon={faBed}/>
                <span>Attractions</span>
            </div>
            <div className="item">
                <FontAwesomeIcon icon={faTaxi}/>
                <span>taxis</span>
            </div>
        </div>
        <h1 className="title">Find your next stay</h1>
        <p className="description">Search low prices on hotels, homes and much more...</p>
        <button className="button">Sign in / Register</button>
        <div className="search">
            <div className="item">
                <FontAwesomeIcon icon={faBed} className="icon"></FontAwesomeIcon>
                <input type="text" placeholder='Where are you going?' className='input'/>
            </div>
            <div className="item">
                <FontAwesomeIcon icon={faCalendarDays} className="icon"></FontAwesomeIcon>
                <span className='text'>date to date</span>
                <DateRange 
                    editableDateInputs={true} 
                    onChange={item => setDate(item.selection)}
                    moveRangeOnFirstSelection={false}
                    ranges={[date]}
                    />
            </div>
            <div className="item">
                <FontAwesomeIcon icon={faPerson} className="icon"></FontAwesomeIcon>
                <span className='text'>2 adults 0 children 1 room</span>
            </div>
            <div className="item">
                <button className="button">Search</button>
            </div>
        </div>
    </div>
    </div>
  )
}

export default Header
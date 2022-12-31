import React, { useContext, useState } from 'react'
import "./Header.scss"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome"
import {faBed, faPlane, faCar, faTaxi, faCalendarDays, faPerson} from '@fortawesome/free-solid-svg-icons'
import {DateRange} from 'react-date-range';
import 'react-date-range/dist/styles.css';
import 'react-date-range/dist/theme/default.css';
import { addDays, format} from 'date-fns/esm';
import { Navigate, useNavigate } from 'react-router';
import { SearchContext } from '../../context/searchContext';
import { AuthContext } from '../../context/authContext';

interface DateTime {
    startDate?: Date | undefined,
    endDate?: Date | undefined,
    key?: string | undefined
}

type HeaderProps = {
    type?: string
}
const Header = ({type}:HeaderProps) => {
    const {state} = useContext(AuthContext);
    const user = state.user;

    const navigate = useNavigate();
    const [openDate, setOpenDate] = useState(false);
    const [date, setDate] = useState<DateTime>(
        {
        startDate: new Date(),
        endDate: addDays(new Date(), 3),
        key: 'selection'
        }
    )
    const [openOptions, setOpenOptions] = useState(false);
    const [options, setOptions] = useState({
        adult: 1,
        children: 0,
        roomNumber: 1
    })
    const [destination, setDestination] = useState("");

    const handleOptions = (name:'adult' | 'children' |'roomNumber', 
                            operation: 'i' | 'd') => {
        setOptions(prev => {
            return {...prev, [name]: operation === 'i' ? options[name] + 1 : options[name] - 1}
        })
    }

    const {dispatch} = useContext(SearchContext);


    const handleSearch = () => {
        dispatch(
            {
                type: "NEW_SEARCH",
                payload: {destination, date, options}
            }
        )
        navigate("/hotels", {state:{destination, date, options}});
    }




  return (
    <div className="header">
        <div className={type === 'list' ? "container lightMode" : "container"}>
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
        {type !=='list' && <>
        <h1 className="title">Find your next stay</h1>
        <p className="description">Search low prices on hotels, homes and much more...</p>
        {!user && <button className="button">Sign in / Register</button> }
        <div className="search">
            <div className="item">
                <FontAwesomeIcon icon={faBed} className="icon"></FontAwesomeIcon>
                <input type="text" placeholder='Where are you going?' className='input'
                onChange={(e)=>setDestination(e.target.value)}/>
            </div>
            <div className="item">
                <FontAwesomeIcon icon={faCalendarDays} className="icon"></FontAwesomeIcon>
                <span className='text' onClick={() => setOpenDate(!openDate)}> 
                {`${format(date.startDate!, "MM/dd/yyyy")} to 
                    ${format(date.endDate!, "MM/dd/yyyy")}`}
                    </span>
                {openDate && <DateRange 
                    editableDateInputs={true} 
                    onChange={item => setDate(item.selection)}
                    moveRangeOnFirstSelection={false}
                    ranges={[date]}
                    minDate={new Date()}
                    className="date"
                    /> }
            </div>
            <div className="item">
                <FontAwesomeIcon icon={faPerson} className="icon"></FontAwesomeIcon>
                <span className='text' onClick={()=>setOpenOptions(!openOptions)}>
                    {`${options.adult} adult - ${options.children} children - ${options.roomNumber} room`}</span>
                    {openOptions && <div className='options'>
                        <div className="item">
                            <span className='text'>Adult</span>
                            <div className="counter">
                                <button className="button" disabled={options.adult <= 1}
                                onClick={()=>handleOptions("adult", "d")}>-</button>
                                <span className='number'>{options.adult}</span>
                                <button className="button" onClick={()=>handleOptions("adult", "i")}>+</button>
                            </div>
                            
                        </div>
                        <div className="item">
                            <span className='text'>Children</span>
                            <div className="counter">
                                <button className="button" disabled={options.children <= 0}
                                onClick={()=>handleOptions("children", "d")}>-</button>
                                <span className='number'>{options.children}</span>
                                <button className="button" onClick={()=>handleOptions("children", "i")}>+</button>
                            </div>
                        </div>
                        <div className="item">
                            <span className='text'>Room</span>
                            <div className="counter">
                                <button className="button" disabled={options.roomNumber <= 1}
                                onClick={()=>handleOptions("roomNumber", "d")}>-</button>
                                <span className='number'>{options.roomNumber}</span>
                                <button className="button" onClick={()=>handleOptions("roomNumber", "i")}>+</button>
                            </div>
                        </div>
                    </div>}
            </div>
            <div className="item">
                <button className="button" onClick={handleSearch}>Search</button>
            </div>
        </div> </>}
    </div>
    </div>
  )
}

export default Header
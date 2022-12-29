import React, { useState } from 'react'
import './List.scss'
import Header from '../../components/header/Header'
import Navbar from '../../components/navbar/Navbar'
import { useLocation } from 'react-router'
import { DateRange } from 'react-date-range'
import SearchItem from '../../components/searchItem/SearchItem'
import { addDays, format} from 'date-fns/esm';


const List = () => {

  const location = useLocation();
  const [destination, setDestination] = useState(location?.state?.destination ? location.state.destination : "Hong Kong");
  const [date, setDate] = useState(location?.state?.date ? location.state.date : {
    startDate: new Date(),
    endDate: addDays(new Date(), 3),
    key: 'selection'
    });
  const [openDate, setOpenDate] = useState(false);
  const [options, setOptions] = useState(location?.state?.options ? location.state.options : {
    adult: 1,
    children: 0,
    roomNumber: 1
});

  console.log(location.state)

  return (
    <div>
      <Navbar/>
      <Header type='list'/>
      <div className="container">
        <div className="wrapper">
          <div className="search">
            <h1 className="title">Search</h1>
            <div className="item">
              <label>Distination</label>
              <input placeholder={destination} type="text" />
            </div>
            <div className="item">
              <label>Check-in-date</label>
              <span onClick={()=>setOpenDate(!openDate)}>
                {`${format(date.startDate, "MM/dd/yyyy")} to ${format(date.endDate, "MM/dd/yyyy")}`}</span>
              {openDate && <DateRange  onChange={item=>setDate(item.selection)} minDate={new Date()} ranges={[date]}/>}
            </div>
            <div className="item">
              <label>Options</label>
              <div className="options">
              <div className="option">
                <span className="text">Minimum price <small>per night</small></span>
                <input type="number" className="input" />
              </div>
              <div className="option">
                <span className="text">Maximum price <small>per night</small></span>
                <input type="number" className="input" />
              </div>
              <div className="option">
                <span className="text">Adult</span>
                <input type="number" min={1} placeholder={options.adult} className="input" />
              </div>
              <div className="option">
                <span className="text">Children</span>
                <input type="number" min={0} placeholder={options.children} className="input" />
              </div>
              <div className="option">
                <span className="text">Room</span>
                <input type="number" min={1} placeholder={options.roomNumber} className="input" />
              </div>
              </div>
              
            </div>
            <button className="searchBtn">Search</button>
          </div>
          <div className="result">
            <SearchItem/>
            <SearchItem/>
            <SearchItem/>
            <SearchItem/>
            <SearchItem/>
            <SearchItem/>
            <SearchItem/>
            <SearchItem/>

          </div>
        </div>
      </div>
    </div>
  )
}

export default List
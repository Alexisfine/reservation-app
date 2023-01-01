import React, { useState } from 'react'
import './List.scss'
import Header from '../../components/header/Header'
import Navbar from '../../components/navbar/Navbar'
import { useLocation } from 'react-router'
import { DateRange } from 'react-date-range'
import SearchItem from '../../components/searchItem/SearchItem'
import { addDays, format} from 'date-fns/esm';

import useFetch from '../../hooks/useFetch'
import { IHotel } from '../../dataTypes/ApiTypes'


const List = () => {

  const location = useLocation();
  const [destination, setDestination] = useState(location?.state?.destination ? location.state.destination : "Berlin");
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
  const [min, setMin] = useState<String|undefined>();
  const [max, setMax] = useState<String|undefined>();
  

  const {loading, data, error, reFetch} = useFetch(`/hotels/v1/find/city?city=${destination}&min=${min || 0}&max=${max || 999}`);
  const info = data! as IHotel[];
  console.log(data);

  const handleSearch = () => {
    reFetch();
  }


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
                <input type="number" className="input" 
                  onChange={e=>setMin(e.target.value)}/>
              </div>
              <div className="option">
                <span className="text">Max imum price <small>per night</small></span>
                <input type="number" className="input" 
                  onChange={e=>setMax(e.target.value)}/>
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
            <button className="searchBtn" onClick={handleSearch}>Search</button>
          </div>
          <div className="result">
            {loading ? "loading" :
              <>
              {info!.map(item => (
                <SearchItem key={item.id} item={item}/>
              ))}
              </>}
          </div>
        </div>
      </div>
    </div>
  )
}

export default List
import { faCircleXmark } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import axios from 'axios'
import React, { useContext, useState } from 'react'
import { Navigate, useNavigate } from 'react-router-dom'
import { SearchContext } from '../../context/searchContext'
import { IHotel, IRoom } from '../../dataTypes/ApiTypes'
import useFetch from '../../hooks/useFetch'
import   './Reserve.scss'
import { faCalendarDays } from '@fortawesome/free-solid-svg-icons'
import { addDays, format} from 'date-fns/esm';
import {DateRange} from 'react-date-range';


interface DateTime {
    startDate?: Date | undefined,
    endDate?: Date | undefined,
    key?: string | undefined
}

type ReserveProps = {
    setOpen: (open: boolean) => void,
    hotelId: string,
    data: IHotel
}
const Reserve = ({setOpen, hotelId, data}:ReserveProps) => {
    const navigate = useNavigate();
    const [selectedRooms, setSelectRooms] = useState<String[]>([])
    const rooms = data.rooms as IRoom[];
    const handleSelect = (e:React.ChangeEvent<HTMLInputElement>) => {
        const selected = e.target.checked;
        const value = e.target.value;
        setSelectRooms(selected ? [...selectedRooms, value] : selectedRooms.filter(r=>r!==value))
    }

    const {dispatch, state} = useContext(SearchContext);
    const date = state.date;
    const [myDate, setMyDate] = useState<DateTime>(
        {startDate: date[0] ? date[0] : new Date(),
        endDate: date[1] ? date[1] : addDays(new Date(), 3),
        key:'selection'});
    // const getDatesInRange = (startdate:Date|number, endDate:Date|number) => {
    //     const start = new Date(startdate)
    //     const end = new Date(endDate)
    //     const date = new Date(start.getTime());

    //     let dates = []
    //     while (date <= end) {
    //         dates.push(new Date(date).getTime())
    //         date.setDate(date.getDate() + 1);
    //     }
    //     return dates;
    // }
    //const allDates = getDatesInRange(date[0].getTime(), date[1].getTime());
    const isAvailable = (room : IRoom) => {
        console.log(room.unavailableDates)
        console.log(myDate.startDate!.getMonth(), myDate.startDate!.getDate())
        const isFound = room.unavailableDates.map(date =>
            (myDate.startDate!.getMonth() === date.timeSlot[1] - 1 && myDate.startDate!.getDate() === date.timeSlot[2])
            || (myDate.endDate!.getMonth() === date.timeSlot[1] - 1 && myDate.endDate!.getDate() === date.timeSlot[2])) 
        const available = isFound.filter(element => element === true).length === 0;   
        console.log(available) 
        return available;
        
    }


    const handleClick = async () => {
        try {
            console.log(selectedRooms)
            await Promise.all(selectedRooms.map((room)=>{
                const res = axios.put(`/rooms/v1/reserve/${room}`, {
                    startDate: myDate!.startDate!.getTime(),
                    endDate: myDate!.endDate!.getTime(),
                }, 
                {headers: 
                    {
                        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrZWlyYSIsImlhdCI6MTY3MjU5MDMxNiwiZXhwIjoxNjcyNTkzOTE2fQ.qWnT2GMi7ZBoaKWC6t_4_5-AsK0zkwvZ8R90PDRlN5U"}}
                )
                navigate('/')

            }))

        } catch (err) {
            console.log(err);
        }
    }
  return (
    <div className='reserve'>
        <div className="reserveContainer">
            <FontAwesomeIcon icon={faCircleXmark} className="close"onClick={() => setOpen(false)}/>
            <div className="selection">
                <div className="dateSelection">
                    <FontAwesomeIcon icon={faCalendarDays} className="icon"></FontAwesomeIcon>
                    <span className='text'> 
                    {`${format(myDate.startDate!, "MM/dd/yyyy")} to 
                        ${format(myDate.endDate!, "MM/dd/yyyy")}`}
                        </span>
                    <DateRange 
                        editableDateInputs={true} 
                        onChange={item => setMyDate(item.selection)}
                        moveRangeOnFirstSelection={false}
                        ranges={[myDate]}
                        minDate={new Date()}
                        className="date"
                        /> 
                </div>
            <div className="roomSelection">
                <span>Select your rooms:</span>
                {rooms.map(room=>(
                    <div className="item" key={room.id}>
                        <div className="info">
                            <div className="title">{room.title}</div>
                            <div className="description">{room.description}</div>
                            <div className="maxPeople">Max people: <b>{room.maxPeople}</b></div>
                            <div className="price">Price: ${room.price} per night</div>
                        </div>
                        <div className="room" key={room.id}>
                                    <label>{room.roomNumber}</label>
                                    <input type="checkbox" value={room.id} disabled={!isAvailable(room)}
                                    onChange={(e) => handleSelect(e)}></input>
                                </div>
                    </div>
                ))}
            </div>
            </div>
            <button className="button" onClick={handleClick}>Reserve Now!</button>

            
        </div>

    </div>
  )
}

export default Reserve
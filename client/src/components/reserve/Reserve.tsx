import { faCircleXmark } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import React, { useContext, useState } from 'react'
import { SearchContext } from '../../context/searchContext'
import { IHotel, IRoom } from '../../dataTypes/ApiTypes'
import useFetch from '../../hooks/useFetch'
import   './Reserve.scss'

type ReserveProps = {
    setOpen: (open: boolean) => void,
    hotelId: string,
    data: IHotel
}
const Reserve = ({setOpen, hotelId, data}:ReserveProps) => {
    const [selectedRooms, setSelectRooms] = useState<String[]>([])
    const rooms = data.rooms as IRoom[];
    const handleSelect = (e:React.ChangeEvent<HTMLInputElement>) => {
        const selected = e.target.checked;
        const value = e.target.value;
        setSelectRooms(selected ? [...selectedRooms, value] : selectedRooms.filter(r=>r!==value))
    }

    const {state} = useContext(SearchContext);
    const date = state.date;
    const getDatesInRange = (startdate:Date|number, endDate:Date|number) => {
        const start = new Date(startdate)
        const end = new Date(endDate)
        const date = new Date(start.getTime());

        let dates = []
        while (date <= end) {
            dates.push(new Date(date).getTime())
            date.setDate(date.getDate() + 1);
        }
        return dates;
    }
    const allDates = getDatesInRange(date[0], date[1]);
    const isAvailable = (room : IRoom) => {
        const isFound = room.unavailableDates.some(d=>allDates.includes(new Date(d).getTime()))
        return !isFound;
    }


    const handleClick = () => {

    }
  return (
    <div className='reserve'>
        <div className="reserveContainer">
            <FontAwesomeIcon icon={faCircleXmark} className="close"onClick={() => setOpen(false)}/>
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
            <button className="button" onClick={handleClick}>Reserve Now!</button>
        </div>
    </div>
  )
}

export default Reserve
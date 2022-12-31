import React from 'react'
import './SearchItem.scss'
import { IHotel } from '../../dataTypes/ApiTypes'
import { Link } from 'react-router-dom'

interface SearchItemProps {
  item: IHotel
}

const SearchItem = ({item}:SearchItemProps) => {
  return (
    <div className="searchItem">
        <img
        src="https://cf.bstatic.com/xdata/images/hotel/square600/261707778.webp?k=fa6b6128468ec15e81f7d076b6f2473fa3a80c255582f155cae35f9edbffdd78&o=&s=1"
        alt=""
        className="img"
        />
        <div className="desc">
            <h1 className="title">{item.name}</h1>
            <span className="distance">{item.distance}m from Downtown</span>
            <span className="taxi">Free airport taxi</span>
            <span className="subtitle">
            Studio Apartment with Air conditioning
            </span>
            <span className="features">
            {item.description}
            </span>
            <span className="cancel">Free cancellation </span>
            <span className="cancelSubtitle">
            You can cancel later, so lock in this great price today!
            </span>
        </div>
        <div className="details">
       {item.rating > 0 && <div className="rating">
          <span>Excellent</span>
          <button>{item.rating}</button>
        </div>}
        <div className="text">
          <span className="price">${item.cheapestPrice}</span>
          <span className="tax">Includes taxes and fees</span>
          <Link to={`/hotels/${item.id}`}>
            <button className="checkButton">See availability</button>
          </Link>
        </div>
      </div>

    </div>
  )
}

export default SearchItem
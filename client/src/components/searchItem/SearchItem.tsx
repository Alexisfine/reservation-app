import React from 'react'
import './SearchItem.scss'

const SearchItem = () => {
  return (
    <div className="searchItem">
        <img
        src="https://cf.bstatic.com/xdata/images/hotel/square600/261707778.webp?k=fa6b6128468ec15e81f7d076b6f2473fa3a80c255582f155cae35f9edbffdd78&o=&s=1"
        alt=""
        className="img"
        />
        <div className="desc">
            <h1 className="title">Tower Street Apartments</h1>
            <span className="distance">500m from center</span>
            <span className="taxi">Free airport taxi</span>
            <span className="subtitle">
            Studio Apartment with Air conditioning
            </span>
            <span className="features">
            Entire studio • 1 bathroom • 21m² 1 full bed
            </span>
            <span className="cancel">Free cancellation </span>
            <span className="cancelSubtitle">
            You can cancel later, so lock in this great price today!
            </span>
        </div>
        <div className="details">
        <div className="rating">
          <span>Excellent</span>
          <button>8.9</button>
        </div>
        <div className="text">
          <span className="price">$112</span>
          <span className="tax">Includes taxes and fees</span>
          <button className="checkButton">See availability</button>
        </div>
      </div>

    </div>
  )
}

export default SearchItem
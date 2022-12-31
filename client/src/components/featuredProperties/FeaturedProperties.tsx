import React, {useEffect, useState} from 'react'
import './FeaturedProperties.scss'
import useFetch from '../../hooks/useFetch';
import { IHotel } from '../../dataTypes/ApiTypes';


const FeaturedProperties = () => {
  const {loading, data, error} = useFetch("/hotels/v1/find/featured?featured=true&limit=4");

  const info = data! as IHotel[];

  return (
    <div className="fp">
        {loading ? "Loading" : 
        info?.map(item => (
          <div className="item" key={item.id}>
        <img
          src="https://cf.bstatic.com/xdata/images/hotel/square600/13125860.webp?k=e148feeb802ac3d28d1391dad9e4cf1e12d9231f897d0b53ca067bde8a9d3355&o=&s=1"
          alt=""
          className="image"
        />
        <span className="name">{item.name}</span>
        <span className="city">{item.city}</span>
        <span className="price">Starting from ${item.cheapestPrice}</span>
        {item.rating &&<div className="rating">
          <button>{item.rating}</button>
          <span>Excellent</span>
        </div>}
      </div>
        ))}
    </div>
  )
}

export default FeaturedProperties
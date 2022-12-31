import { faCircleArrowLeft, faCircleArrowRight, faCircleXmark, faLocationDot } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { parseWithOptions } from 'date-fns/fp'
import React, { useContext, useState } from 'react'
import Footer from '../../components/footer/Footer'
import Header from '../../components/header/Header'
import MailList from '../../components/mailList/MailList'
import Navbar from '../../components/navbar/Navbar'
import './Hotel.scss'
import useFetch from '../../hooks/useFetch'
import { useLocation, useNavigate } from 'react-router-dom'
import { IDate, IHotel } from '../../dataTypes/ApiTypes'
import { SearchContext } from '../../context/searchContext'
import { AuthContext } from '../../context/authContext'
import Reserve from '../../components/reserve/Reserve'
const Hotel = () => {

  const [slideNumber, setSlideNumber] = useState(0);
  const [open, setOpen] = useState(false);
  const [openBook, setOpenBook] = useState(false);
  const location = useLocation().pathname;
  const id = location.split('/')[2];
 
  const {loading, data, error} = useFetch(`/hotels/v1/${id}`);
  const info = data as unknown as IHotel;


  const photos = [
    {
      src: "https://cf.bstatic.com/xdata/images/hotel/max1280x900/261707778.jpg?k=56ba0babbcbbfeb3d3e911728831dcbc390ed2cb16c51d88159f82bf751d04c6&o=&hp=1",
    },
    {
      src: "https://cf.bstatic.com/xdata/images/hotel/max1280x900/261707367.jpg?k=cbacfdeb8404af56a1a94812575d96f6b80f6740fd491d02c6fc3912a16d8757&o=&hp=1",
    },
    {
      src: "https://cf.bstatic.com/xdata/images/hotel/max1280x900/261708745.jpg?k=1aae4678d645c63e0d90cdae8127b15f1e3232d4739bdf387a6578dc3b14bdfd&o=&hp=1",
    },
    {
      src: "https://cf.bstatic.com/xdata/images/hotel/max1280x900/261707776.jpg?k=054bb3e27c9e58d3bb1110349eb5e6e24dacd53fbb0316b9e2519b2bf3c520ae&o=&hp=1",
    },
    {
      src: "https://cf.bstatic.com/xdata/images/hotel/max1280x900/261708693.jpg?k=ea210b4fa329fe302eab55dd9818c0571afba2abd2225ca3a36457f9afa74e94&o=&hp=1",
    },
    {
      src: "https://cf.bstatic.com/xdata/images/hotel/max1280x900/261707389.jpg?k=52156673f9eb6d5d99d3eed9386491a0465ce6f3b995f005ac71abc192dd5827&o=&hp=1",
    },
  ];
  const MILL_SEC_PER_DAY = 1000 * 60 * 60 * 24;
  const {state} = useContext(SearchContext);
  const dateInfo = state.date as unknown as IDate;
  const options = state.options;

  const dateDifference = (start:Date, end:Date) => {
    if (!start || ! end) return 3;
    const timeDiff = Math.abs(end.getTime() - start.getTime());
    const diffDays = Math.ceil(timeDiff / MILL_SEC_PER_DAY);
    return diffDays;
  }

  console.log(state)

  const days = dateDifference(dateInfo?.startDate, dateInfo?.endDate)


  const handleOpen = (i:number) => {
    setSlideNumber(i);
    setOpen(true);
  }

  const handleMove = (direction:"l"|"r") =>{
    let newSlideNumber;
    if (direction === "l") {
      newSlideNumber = slideNumber === 0 ? 5 : slideNumber - 1;
      setSlideNumber(newSlideNumber);

    }
    else if (direction === "r") {
      newSlideNumber = slideNumber === 5 ? 0 : slideNumber + 1;
      setSlideNumber(newSlideNumber);
    } 
  }

  const navigate = useNavigate();

  const {state:authState} = useContext(AuthContext);
  const user = authState.user;

  const handleClick = () => {
    if (user) {
      setOpenBook(true)

    } else {
      navigate("/login")
    }
  }


  return (
    <div>
      <Navbar/>
      <Header type="list"/>
      {loading ? "loading" : (<div className="hotelContainer">
        {open && <div className="silder">
          <FontAwesomeIcon icon={faCircleXmark} className="close" onClick={()=>setOpen(false)}/>
          <FontAwesomeIcon icon={faCircleArrowLeft} className="arrow" onClick={()=>handleMove("l")}/>
          <div className="silderWrapper">
            <img src={photos[slideNumber].src} alt="" className="silderImg" />
          </div>
          <FontAwesomeIcon icon={faCircleArrowRight} className="arrow" onClick={()=>handleMove("r")}/>

          </div>}
        <div className="hotelWrapper">
          <button className="book" onClick={handleClick}>{info.rooms?.length !== 0 ? "Reserve or Book Now!" :"No rooms are available"}</button>
          <h1 className="title">{info.title}</h1>
          <div className="address">
            <FontAwesomeIcon icon={faLocationDot}/>
            <span>{info.address}</span></div>
            <span className="distance">
              Excellent location - {info.distance}m from center
            </span>
            <span className="priceHighlight">
              Book a stay over ${info.cheapestPrice} at this property and get a free airport taxi
            </span>
            <div className="images">
                {photos.map((photo,i) => (
                  <div className="imageWrapper" key={i}>
                    <img src={photo.src} alt="" className="image" onClick={() => handleOpen(i)}/>
                  </div>
                ))}
            </div>
            <div className="details">
              <div className="texts">
                <h1 className="textTitle">{info.title}</h1>
                <p className="desc">
                  {info.description}
                </p>
              </div>
              <div className="price">
                <h1>Perfect for a {days ? days : "9"}-night stay!</h1>
                <span>
                  Located in the real heart of Krakow, this property has an
                  excellent location score of 9.8!
                </span>
                <h2>
                  <b>${days * info.cheapestPrice * options.roomNumber!} </b> ({days ? days : "9"} nights)
                </h2>
                <button onClick={handleClick} disabled={info.rooms?.length === 0}>
                  {info.rooms?.length !== 0 ? "Reserve or Book Now!" :"No rooms are available"}</button>
              </div>
            </div>
          </div>
          <MailList/>
          <Footer/>
        </div>)}
        {openBook && <Reserve setOpen={setOpenBook} hotelId={id} data={info}></Reserve>}
    </div>
  )
}

export default Hotel
export interface IHotel {
  id: string,
  name: string,
  city: string,
  hotelType: string,
  address: string,
  distance: string,
  description: string,
  cheapestPrice: number,
  title: string,
  rating: number,
  featured: boolean,
  createdAt?: any,
  updatedAt?: any,
  rooms?: any,
}

export interface IUser {

    id: string,
    username: string,
    email:string,
    
}

interface unavailableDateType {
  id: string,
  rooms: any,
  timeSlot: number[]
}

export interface IRoom {
  id: string,
  title: string,
  roomNumber: string,
  price: number,
  description: string,
  maxPeople: number,
  roomType: string,
  unavailableDates: unavailableDateType[]
}

export interface IDate {
  startDate: Date, key: string, endDate: Date
}


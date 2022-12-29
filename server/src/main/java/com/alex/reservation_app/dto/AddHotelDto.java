package com.alex.reservation_app.dto;

public class AddHotelDto {
    private String name;
    private String hotelType;
    private String city;
    private String address;
    private String distance;
    private String title;
    private String description;
    private String cheapestPrice;

    public AddHotelDto(String name,
                       String hotelType,
                       String city,
                       String address,
                       String distance,
                       String title,
                       String description,
                       String cheapestPrice) {
        this.name = name;
        this.hotelType = hotelType;
        this.city = city;
        this.address = address;
        this.distance = distance;
        this.title = title;
        this.description = description;
        this.cheapestPrice = cheapestPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCheapestPrice() {
        return cheapestPrice;
    }

    public void setCheapestPrice(String cheapestPrice) {
        this.cheapestPrice = cheapestPrice;
    }


    @Override
    public String toString() {
        return "AddHotelDto{" +
                "name='" + name + '\'' +
                ", hotelType='" + hotelType + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", distance='" + distance + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cheapestPrice='" + cheapestPrice + '\'' +
                '}';
    }
}

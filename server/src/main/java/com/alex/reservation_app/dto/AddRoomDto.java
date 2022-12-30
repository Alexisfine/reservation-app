package com.alex.reservation_app.dto;

import com.alex.reservation_app.model.Room;
import com.alex.reservation_app.utils.RoomType;

public class AddRoomDto {
    private RoomType roomType;
    private String title;
    private String roomNumber;
    private String price;
    private String description;
    private String maxPeople;

    public AddRoomDto() {
    }

    public AddRoomDto(RoomType roomType, String title, String roomNumber, String price, String description, String maxPeople) {
        this.roomType = roomType;
        this.title = title;
        this.roomNumber = roomNumber;
        this.price = price;
        this.description = description;
        this.maxPeople = maxPeople;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(String maxPeople) {
        this.maxPeople = maxPeople;
    }

    @Override
    public String toString() {
        return "AddRoomDto{" +
                "roomType=" + roomType +
                ", title='" + title + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", maxPeople='" + maxPeople + '\'' +
                '}';
    }
}

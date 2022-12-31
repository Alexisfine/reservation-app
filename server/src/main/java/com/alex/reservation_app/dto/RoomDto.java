package com.alex.reservation_app.dto;

import com.alex.reservation_app.model.Date;
import com.alex.reservation_app.model.Hotel;
import com.alex.reservation_app.utils.RoomType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomDto {

    private UUID id;
    private UUID hotelId;
    private String title;
    private String roomNumber;
    private Double price;
    private String description;
    private Integer maxPeople; // required
    private RoomType roomType;
    private List<Date> unavailableDates;

    public RoomDto() {
    }

    public RoomDto(UUID id,
                   UUID hotelId,
                   String title,
                   String roomNumber,
                   Double price,
                   String description,
                   Integer maxPeople,
                   RoomType roomType,
                   List<Date> unavailableDates) {
        this.id = id;
        this.hotelId = hotelId;
        this.title = title;
        this.roomNumber = roomNumber;
        this.price = price;
        this.description = description;
        this.maxPeople = maxPeople;
        this.roomType = roomType;
        this.unavailableDates = unavailableDates;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getHotelId() {
        return hotelId;
    }

    public void setHotelId(UUID hotelId) {
        this.hotelId = hotelId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public List<Date> getUnavailableDates() {
        return unavailableDates;
    }

    public void setUnavailableDates(List<Date> unavailableDates) {
        this.unavailableDates = unavailableDates;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", title='" + title + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", maxPeople=" + maxPeople +
                ", roomType=" + roomType +
                ", unavailableDates=" + unavailableDates +
                '}';
    }
}

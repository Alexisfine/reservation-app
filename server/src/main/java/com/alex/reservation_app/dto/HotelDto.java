package com.alex.reservation_app.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class HotelDto {
    private UUID id;
    private String name;
    private String hotelType;
    private String city;
    private String address;
    private String distance;
    private String description;
    private String title;
    private Double rating;
    private Double cheapestPrice;
    private Boolean featured;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public HotelDto(UUID id, String name,
                    String hotelType,
                    String city,
                    String address,
                    String distance,
                    String description,
                    String title,
                    Double rating,
                    Double cheapestPrice,
                    Boolean featured,
                    LocalDateTime createdAt,
                    LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.hotelType = hotelType;
        this.city = city;
        this.address = address;
        this.distance = distance;
        this.description = description;
        this.title = title;
        this.rating = rating;
        this.cheapestPrice = cheapestPrice;
        this.featured = featured;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getCheapestPrice() {
        return cheapestPrice;
    }

    public void setCheapestPrice(Double cheapestPrice) {
        this.cheapestPrice = cheapestPrice;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

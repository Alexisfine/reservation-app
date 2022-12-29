package com.alex.reservation_app.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name="Hotel")
@Table(
        name="hotel",
        uniqueConstraints = {
                @UniqueConstraint(name="hotel_name_unique", columnNames = "name")
        }
)
public class Hotel {
    @Id
    @Column(
            name = "id",
            updatable = false
    )
    private UUID id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "hotel_type",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String hotelType;

    @Column(
            name = "city",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String city;

    @Column(
            name = "address",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String address;

    @Column(
            name = "distance",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String distance;

    @OneToMany(
            mappedBy = "hotel",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(
            mappedBy = "hotel",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Room> rooms = new ArrayList<>();

    @Column(name = "description")
    private String description;


    @Column(name = "rating")
    private double rating;

    @Column(name = "cheapest_price")
    private double cheapestPrice;

    @Column(name = "featured")
    private boolean featured = false;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    @Column(
            name = "updated_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime updated_at;



    public Hotel() {
    }

    public Hotel(String name,
                 String hotelType,
                 String city,
                 String address,
                 String distance,
                 String description,
                 double rating) {
        this.name = name;
        this.hotelType = hotelType;
        this.city = city;
        this.address = address;
        this.distance = distance;
        this.description = description;
        this.rating = rating;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
        this.updated_at = LocalDateTime.now();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.updated_at = LocalDateTime.now();

    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
        this.updated_at = LocalDateTime.now();

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        this.updated_at = LocalDateTime.now();

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        this.updated_at = LocalDateTime.now();

    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
        this.updated_at = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        this.updated_at = LocalDateTime.now();
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void addPhoto(Photo photo) {
        this.photos.add(photo);
        this.updated_at = LocalDateTime.now();
    }

    public void removePhoto(Photo photo) {
        if (this.photos.contains(photo)) {
            this.photos.remove(photo);
        }
        this.updated_at = LocalDateTime.now();
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
        this.updated_at = LocalDateTime.now();
    }

    public void removeRoom(Room room) {
        if (this.rooms.contains(room)) {
            this.rooms.remove(room);
        }
        this.updated_at = LocalDateTime.now();
    }

    public double getCheapestPrice() {
        return cheapestPrice;
    }

    public void setCheapestPrice(double cheapestPrice) {
        this.cheapestPrice = cheapestPrice;
    }
}

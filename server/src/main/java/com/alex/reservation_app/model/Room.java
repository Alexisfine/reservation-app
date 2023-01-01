package com.alex.reservation_app.model;

import com.alex.reservation_app.utils.RoomType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Room")
@Table(
        name = "room",
        uniqueConstraints = {
                @UniqueConstraint(name="room_number_unique", columnNames = "room_number")
        }
)
public class Room {

    @Id
    @Column(
            name = "id",
            updatable = false
    )
    private UUID id;

    @ManyToOne
    @JoinColumn(
            name="hotel_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name="room_hotel_id_fk")
    )
    private Hotel hotel;

    @Column(
            name = "title",
            nullable = false
    )
    private String title;

    @Column(
            name = "room_number",
            nullable = false
    )
    private String roomNumber;


    @Column(
            name = "price",
            nullable = false
    )
    private Double price;

    @Column(
            name = "description",
            nullable = false
    )
    private String description; // required

    @Column(
            name = "max_people",
            nullable = false
    )
    private Integer maxPeople; // required

    @Column(
            name = "room_type",
            nullable = false
    )
    private RoomType roomType;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "room_timetable",
            joinColumns = @JoinColumn(
                    name = "room_id",
                    foreignKey = @ForeignKey(name = "room_timetable_room_id_fk")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "date_id",
                    foreignKey = @ForeignKey(name = "room_timetable_date_id_fk")
            )
    )
    private List<Date> unavailableDates= new ArrayList<>();


    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    public Room() {
    }

    public Room(
            String title,
            String roomNumber,
            Double price,
            String description,
            Integer maxPeople,
            RoomType roomType) {
        this.title = title;
        this.roomNumber = roomNumber;
        this.price = price;
        this.description = description;
        this.maxPeople = maxPeople;
        this.roomType = roomType;
    }

    public UUID getId() {
        return id;
    }

    public Room(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void addHotel() {
        this.hotel.addRoom(this);
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

    public void addUnavailableDate(Date date) {
        unavailableDates.add(date);
        date.getRooms().add(this);
    }

    public void removeUnavailableDate(Date date) {
        unavailableDates.remove(date);
        date.getRooms().remove(this);
    }

    public void addAllUnavailableDates(List<Date> dateList) {
        dateList.forEach(this::addUnavailableDate);
    }

    public void removeUnavailableDates(List<Date> dateList) {
        dateList.forEach(this::removeUnavailableDate);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", maxPeople=" + maxPeople +
                ", roomType=" + roomType +
                ", createdAt=" + createdAt +
                '}';
    }
}

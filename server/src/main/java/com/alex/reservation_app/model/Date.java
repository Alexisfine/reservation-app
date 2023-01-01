package com.alex.reservation_app.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Date")
@Table(name = "date")
public class Date {
    @Id
    @Column(
            name = "id",
            updatable = false
    )
    private UUID id;

    @ManyToMany(
            mappedBy = "unavailableDates",
            fetch = FetchType.LAZY
    )
    private List<Room> rooms = new ArrayList<>();


    @Column(name = "time_slot")
    private LocalDate timeSlot;

    public Date() {
    }

    public Date(LocalDate timeSlot) {
        this.id = UUID.randomUUID();
        this.timeSlot = timeSlot;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getTimeSlot() {
        return timeSlot;
    }

    public void setDate(LocalDate timeSlot) {

        this.timeSlot = timeSlot;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Date{" +
                "id=" + id +
                ", timeSlot=" + timeSlot +
                '}';
    }
}

package com.alex.reservation_app.model;

import jakarta.persistence.*;

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
            mappedBy = "unavailableDates"
    )
    private List<Room> rooms = new ArrayList<>();


    @Column(name = "time_slot")
    private LocalDateTime timeSlot;

    public Date() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(LocalDateTime timeSlot) {
        this.timeSlot = timeSlot;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return "Date{" +
                "id=" + id +
                ", timeSlot=" + timeSlot +
                '}';
    }
}

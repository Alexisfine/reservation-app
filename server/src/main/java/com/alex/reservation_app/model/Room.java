package com.alex.reservation_app.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Room")
@Table(name = "room")
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
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;


}

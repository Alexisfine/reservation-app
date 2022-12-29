package com.alex.reservation_app.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Photo")
@Table(name = "photo")
public class Photo {
    @Id
    @Column(
            name = "id",
            updatable = false
    )
    private UUID id;

    @Column(
            name = "link",
            nullable = false
    )
    private String link;

    @ManyToOne
    @JoinColumn(
            name="hotel_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name="photo_hotel_id_fk")
    )
    private Hotel hotel;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    public Photo() {
    }

    public Photo(String link, Hotel hotel) {
        this.id = UUID.randomUUID();
        this.link = link;
        this.hotel = hotel;
        this.createdAt = LocalDateTime.now();
    }
}

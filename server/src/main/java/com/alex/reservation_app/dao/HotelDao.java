package com.alex.reservation_app.dao;

import com.alex.reservation_app.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HotelDao extends JpaRepository<Hotel, UUID> {
}

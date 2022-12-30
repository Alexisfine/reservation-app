package com.alex.reservation_app.dao;

import com.alex.reservation_app.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomDao extends JpaRepository<Room, UUID> {
    boolean existsById(UUID id);

    boolean existsByRoomNumber(String number);

}

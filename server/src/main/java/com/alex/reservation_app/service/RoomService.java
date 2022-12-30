package com.alex.reservation_app.service;

import com.alex.reservation_app.dto.AddRoomDto;
import com.alex.reservation_app.model.Room;

import java.util.List;
import java.util.UUID;

public interface RoomService {
    Room createRoom(AddRoomDto addRoomDto, UUID id);

    Room updateRoomById(AddRoomDto addRoomDto, UUID uuid);

    boolean removeRoomById(UUID id);

    List<Room> getAllRooms();

    Room getRoomById(UUID id);
}

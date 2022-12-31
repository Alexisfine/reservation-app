package com.alex.reservation_app.service;

import com.alex.reservation_app.dto.AddRoomDto;
import com.alex.reservation_app.dto.RoomDto;
import com.alex.reservation_app.model.Room;

import java.util.List;
import java.util.UUID;

public interface RoomService {
    RoomDto createRoom(AddRoomDto addRoomDto, UUID id);

    RoomDto updateRoomById(AddRoomDto addRoomDto, UUID uuid);

    boolean removeRoomById(UUID id);

    List<RoomDto> getAllRooms();

    RoomDto getRoomById(UUID id);
}

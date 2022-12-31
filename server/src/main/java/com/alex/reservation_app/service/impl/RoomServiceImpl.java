package com.alex.reservation_app.service.impl;

import com.alex.reservation_app.dao.HotelDao;
import com.alex.reservation_app.dao.RoomDao;
import com.alex.reservation_app.dto.AddRoomDto;
import com.alex.reservation_app.dto.RoomDto;
import com.alex.reservation_app.exception.HotelNotFoundException;
import com.alex.reservation_app.exception.IllegalOperationException;
import com.alex.reservation_app.exception.RoomNotFoundException;
import com.alex.reservation_app.model.Hotel;
import com.alex.reservation_app.model.Room;
import com.alex.reservation_app.service.RoomService;
import com.alex.reservation_app.utils.UpdateColumnUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RoomServiceImpl implements RoomService {
    private RoomDao roomDao;
    private HotelDao hotelDao;

    @Autowired
    public RoomServiceImpl(RoomDao roomDao, HotelDao hotelDao) {
        this.roomDao = roomDao;
        this.hotelDao = hotelDao;
    }

    @Override
    public RoomDto createRoom(AddRoomDto addRoomDto, UUID id) {
        // Find the hotel
        Hotel hotel = hotelDao
                .findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel does not exist"));


        if (roomDao.existsByRoomNumber(addRoomDto.getRoomNumber()))
            throw new RoomNotFoundException("Room already exists");

        // Create Room instance
        Room room = new Room();
        room.setTitle(addRoomDto.getTitle());
        room.setDescription(addRoomDto.getDescription());
        room.setMaxPeople(Integer.parseInt(addRoomDto.getMaxPeople()));
        room.setPrice(Double.parseDouble(addRoomDto.getPrice()));
        room.setRoomType(addRoomDto.getRoomType());
        room.setRoomNumber(addRoomDto.getRoomNumber());
        room.setHotel(hotel);
        room.setId(UUID.randomUUID());
        room.setCreatedAt(LocalDateTime.now());
        Room savedRoom = roomDao.save(room);

        RoomDto returnRoom = mapRoomToRoomDto(savedRoom);
        return returnRoom;
    }



    @Override
    public RoomDto updateRoomById(AddRoomDto addRoomDto, UUID uuid) {
        Room currentRoom = roomDao
                .findById(uuid)
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));
        BeanUtils.copyProperties(addRoomDto, currentRoom, UpdateColumnUtil.getNullPropertyNames(addRoomDto));
        Room savedRoom = roomDao.save(currentRoom);

        return mapRoomToRoomDto(savedRoom);
    }

    @Override
    public boolean removeRoomById(UUID id) {
        Room room = roomDao.findById(id).orElseThrow(() -> new RoomNotFoundException("Cannot find room"));
        try {
            roomDao.delete(room);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<RoomDto> getAllRooms() {
        List<Room> rooms = roomDao.findAll();
        List<RoomDto> roomDtos = rooms
                .stream()
                .map(room -> mapRoomToRoomDto(room))
                .toList();
        return roomDtos;
    }

    @Override
    public RoomDto getRoomById(UUID id) {
        Room room = roomDao.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found"));
        return mapRoomToRoomDto(room);
    }

    private static RoomDto mapRoomToRoomDto(Room savedRoom) {
        RoomDto returnRoom = new RoomDto(
                savedRoom.getId(),
                savedRoom.getHotel().getId(),
                savedRoom.getTitle(),
                savedRoom.getRoomNumber(),
                savedRoom.getPrice(),
                savedRoom.getDescription(),
                savedRoom.getMaxPeople(),
                savedRoom.getRoomType(),
                savedRoom.getUnavailableDates()
        );
        return returnRoom;
    }
}

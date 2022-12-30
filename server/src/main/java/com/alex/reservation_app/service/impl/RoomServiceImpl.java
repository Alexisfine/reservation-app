package com.alex.reservation_app.service.impl;

import com.alex.reservation_app.dao.HotelDao;
import com.alex.reservation_app.dao.RoomDao;
import com.alex.reservation_app.dto.AddRoomDto;
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
    public Room createRoom(AddRoomDto addRoomDto, UUID id) {
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

        savedRoom.setHotel(null);
        return savedRoom;
    }

    @Override
    public Room updateRoomById(AddRoomDto addRoomDto, UUID uuid) {
        Room currentRoom = roomDao
                .findById(uuid)
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));
        BeanUtils.copyProperties(addRoomDto, currentRoom, UpdateColumnUtil.getNullPropertyNames(addRoomDto));
        roomDao.save(currentRoom);
        currentRoom.setHotel(null);
        return currentRoom;
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
    public List<Room> getAllRooms() {
        List<Room> rooms = roomDao.findAll();
        for (Room r : rooms) {
            r.setHotel(null);
        }
        return rooms;
    }

    @Override
    public Room getRoomById(UUID id) {
        Room room = roomDao.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found"));
        room.setHotel(null);
        return room;
    }
}

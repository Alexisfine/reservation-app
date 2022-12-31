package com.alex.reservation_app.controller;

import com.alex.reservation_app.dto.AddRoomDto;
import com.alex.reservation_app.dto.RoomDto;
import com.alex.reservation_app.model.Room;
import com.alex.reservation_app.service.RoomService;
import com.alex.reservation_app.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rooms/v1")
public class RoomController {
    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/auth/{id}")
    // NOTE: this id param is hotel id
    public ResponseEntity<R> addNewRoom(@RequestBody AddRoomDto addRoomDto, @PathVariable("id") UUID uuid) {
        RoomDto room = roomService.createRoom(addRoomDto, uuid);
        return ResponseEntity.ok(new R(room));
    }

    @PutMapping("/auth/{id}")
    public ResponseEntity<R> updateRoomByRoomId(@RequestBody AddRoomDto addRoomDto, @PathVariable("id") UUID uuid) {
        RoomDto room = roomService.updateRoomById(addRoomDto, uuid);
        return ResponseEntity.ok(new R(room));
    }

    @DeleteMapping("/auth/{id}")
    public ResponseEntity<R> deleteRoomBYRoomId(@PathVariable("id") UUID id) {
        boolean success = roomService.removeRoomById(id);
        return ResponseEntity.ok(new R(success));
    }

    @GetMapping
    public ResponseEntity<R> getAllRooms() {
        List<RoomDto> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(new R(rooms));
    }

    @GetMapping("/{id}")
    public ResponseEntity<R> getRoom(@PathVariable("id") UUID id) {
        RoomDto room = roomService.getRoomById(id);
        return ResponseEntity.ok(new R(room));
    }
}

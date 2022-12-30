package com.alex.reservation_app.controller;

import com.alex.reservation_app.dto.AddHotelDto;
import com.alex.reservation_app.dto.HotelDto;
import com.alex.reservation_app.model.Hotel;
import com.alex.reservation_app.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hotels/v1")
public class HotelController {
    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }


    @PostMapping("/auth")
    public ResponseEntity<Hotel> addHotel(@RequestBody AddHotelDto addHotelDto) {
        double cheapestPrice = Double.parseDouble(addHotelDto.getCheapestPrice());
        Hotel newHotel = new Hotel(
                addHotelDto.getName(),
                addHotelDto.getHotelType(),
                addHotelDto.getCity(),
                addHotelDto.getAddress(),
                addHotelDto.getDistance(),
                addHotelDto.getTitle(),
                addHotelDto.getDescription(),
                cheapestPrice);
        return ResponseEntity.ok(hotelService.addHotel(newHotel));
    }


    @PutMapping("/auth/{id}")
    public ResponseEntity<Hotel> updateHotel(@RequestBody HotelDto hotelDto, @PathVariable("id") UUID hotelId) {
        System.out.println(hotelDto);
        return ResponseEntity.ok(hotelService.updateHotel(hotelDto, hotelId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable("id") UUID hotelId) {
        return ResponseEntity.ok(hotelService.getHotelById(hotelId));
    }


    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }


    @DeleteMapping("/auth/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable("id") UUID hotelId) {
        return ResponseEntity.ok(hotelService.deleteHotel(hotelId));
    }
}

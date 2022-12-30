package com.alex.reservation_app.controller;

import com.alex.reservation_app.dto.AddHotelDto;
import com.alex.reservation_app.dto.HotelDto;
import com.alex.reservation_app.model.Hotel;
import com.alex.reservation_app.service.HotelService;
import com.alex.reservation_app.utils.R;
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
    public ResponseEntity<R> addHotel(@RequestBody AddHotelDto addHotelDto) {
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
        return ResponseEntity.ok(new R(hotelService.addHotel(newHotel)));
    }


    @PutMapping("/auth/{id}")
    public ResponseEntity<R> updateHotel(@RequestBody HotelDto hotelDto, @PathVariable("id") UUID hotelId) {
        System.out.println(hotelDto);
        return ResponseEntity.ok(new R(hotelService.updateHotel(hotelDto, hotelId)));
    }


    @GetMapping("/{id}")
    public ResponseEntity<R> getHotel(@PathVariable("id") UUID hotelId) {
        return ResponseEntity.ok(new R(hotelService.getHotelById(hotelId)));
    }


    @GetMapping
    public ResponseEntity<R> getAllHotels() {
        return ResponseEntity.ok(new R(hotelService.getAllHotels()));
    }


    @DeleteMapping("/auth/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable("id") UUID hotelId) {
        return ResponseEntity.ok(hotelService.deleteHotel(hotelId));
    }

    @GetMapping("/countByCity")
    public ResponseEntity<R> countByCity(@RequestParam(name="cities") String cities) {
        String[] cityList = cities.split(",");
        List<Integer> integerList = hotelService.countByCity(cityList);
        return ResponseEntity.ok(new R(integerList));
    }

    @GetMapping("/countByType")
    public ResponseEntity<R> countByType(@RequestParam(name="types") String types) {
        String[] typeList = types.split(",");
        List<Integer> integerList = hotelService.countByType(typeList);
        return ResponseEntity.ok(new R(integerList));
    }

    @GetMapping("/find")
    public ResponseEntity<R> getByFeatured(
            @RequestParam(name="featured") String featured,
            @RequestParam(name="limit") String limit) {
        List<Hotel> hotelList = hotelService
                .getByFeatured(
                        Boolean.parseBoolean(featured),
                        Integer.parseInt(limit));
        return ResponseEntity.ok(new R(hotelList));
    }
}

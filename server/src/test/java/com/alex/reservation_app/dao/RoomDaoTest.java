package com.alex.reservation_app.dao;

import com.alex.reservation_app.model.Hotel;
import com.alex.reservation_app.model.Room;
import com.alex.reservation_app.utils.RoomType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class RoomDaoTest {
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private HotelDao hotelDao;

    @Test
    void RoomDao_ExistsById_ReturnsTrue() {
        // setup
        UUID hotelId = UUID.randomUUID();
        createHotel(hotelId);

        UUID id = UUID.randomUUID();
        Room room = Room.builder()
                .id(id)
                .title("King")
                .roomNumber("983")
                .roomType(RoomType.SINGLE)
                .createdAt(LocalDateTime.now())
                .maxPeople(1)
                .price(404.0)
                .description("Most valuable room ")
                .hotel(hotelDao.findById(hotelId).get())
                .build();

        roomDao.save(room);

        // exec
        boolean exist = roomDao.existsById(id);

        // assert
        assertThat(exist).isTrue();
    }


    @Test
    void RoomDao_ExistsByRoomNumber_ReturnsTrue() {
        // setup
        // setup
        UUID hotelId = UUID.randomUUID();
        createHotel(hotelId);

        UUID id = UUID.randomUUID();
        String roomNumber = "983";
        Room room = Room.builder()
                .id(id)
                .title("King")
                .roomNumber(roomNumber)
                .roomType(RoomType.SINGLE)
                .createdAt(LocalDateTime.now())
                .maxPeople(1)
                .price(404.0)
                .description("Most valuable room ")
                .hotel(hotelDao.findById(hotelId).get())
                .build();

        roomDao.save(room);

        // exec
        boolean exist = roomDao.existsByRoomNumber(roomNumber);

        // assert
        assertThat(exist).isTrue();
    }

    private void createHotel(UUID hotelId) {
        Hotel hotel = Hotel.builder()
                .id(hotelId)
                .name("Grand Hotel")
                .address("London, England")
                .hotelType("Hotel")
                .city("London")
                .distance("505")
                .title("5 star hotel")
                .rooms(new ArrayList<>())
                .photos(new ArrayList<>())
                .cheapestPrice(404.3)
                .featured(true)
                .description("hello")
                .createdAt(LocalDateTime.now())
                .build();

        hotelDao.save(hotel);
    }

}
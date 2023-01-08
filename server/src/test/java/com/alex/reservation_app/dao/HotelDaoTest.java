package com.alex.reservation_app.dao;

import com.alex.reservation_app.model.Hotel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class HotelDaoTest {
    @Autowired
    private HotelDao hotelDao;

    @Test
    void HotelDao_ExistsByName_ReturnsTrue() {
        // setup
        String name = "Grand Hotel";
        Hotel hotel = Hotel.builder()
                .id(UUID.randomUUID())
                .name(name)
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

        // exec
        boolean exists = hotelDao.existsByName(name);

        // assert
        assertThat(exists).isTrue();
    }

    @Test
    void HotelDao_CountByCitiesKeyWord_ReturnsOne() {
        // setup
        String name = "Grand Hotel";
        Hotel hotel = Hotel.builder()
                .id(UUID.randomUUID())
                .name(name)
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

        Hotel hotel2 = Hotel.builder()
                .id(UUID.randomUUID())
                .name("Best hotel")
                .address("Cambridge, England")
                .hotelType("Hotel")
                .city("Cambridge")
                .distance("505")
                .title("Best 5 star hotel")
                .rooms(new ArrayList<>())
                .photos(new ArrayList<>())
                .cheapestPrice(404.3)
                .featured(true)
                .description("hello")
                .createdAt(LocalDateTime.now())
                .build();
        hotelDao.save(hotel2);

        // exec
        Integer counts = hotelDao.countByCitiesKeyWord("London");

        // assert
        assertThat(counts).isEqualTo(1);
    }

    @Test
    void HotelDao_CountByHotelTypeLike_ReturnsTwo() {
        // setup
        String name = "Grand Hotel";
        Hotel hotel = Hotel.builder()
                .id(UUID.randomUUID())
                .name(name)
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

        Hotel hotel2 = Hotel.builder()
                .id(UUID.randomUUID())
                .name("Best hotel")
                .address("Cambridge, England")
                .hotelType("Hotel")
                .city("Cambridge")
                .distance("505")
                .title("Best 5 star hotel")
                .rooms(new ArrayList<>())
                .photos(new ArrayList<>())
                .cheapestPrice(404.3)
                .featured(true)
                .description("hello")
                .createdAt(LocalDateTime.now())
                .build();
        hotelDao.save(hotel2);

        // exec
        Integer hotels = hotelDao.countByHotelTypeLike("Hotel");

        // assert
        assertThat(hotels).isEqualTo(2);
    }

    @Test
    void HotelDao_FindByFeaturedAndCheapestPriceBetween_ReturnsOne() {
        // setup
        String name = "Grand Hotel";
        Hotel hotel = Hotel.builder()
                .id(UUID.randomUUID())
                .name(name)
                .address("London, England")
                .hotelType("Hotel")
                .city("London")
                .distance("505")
                .title("5 star hotel")
                .rooms(new ArrayList<>())
                .photos(new ArrayList<>())
                .cheapestPrice(400)
                .featured(true)
                .description("hello")
                .createdAt(LocalDateTime.now())
                .build();
        hotelDao.save(hotel);

        Hotel hotel2 = Hotel.builder()
                .id(UUID.randomUUID())
                .name("Best hotel")
                .address("Cambridge, England")
                .hotelType("Hotel")
                .city("Cambridge")
                .distance("505")
                .title("Best 5 star hotel")
                .rooms(new ArrayList<>())
                .photos(new ArrayList<>())
                .cheapestPrice(500)
                .featured(true)
                .description("hello")
                .createdAt(LocalDateTime.now())
                .build();
        hotelDao.save(hotel2);

        // exec
        List<Hotel> hotels = hotelDao.findByFeaturedAndCheapestPriceBetween(true, 0, 450);

        // assert
        assertThat(hotels.size()).isEqualTo(1);
    }

    @Test
    void HotelDao_FindByCityIsLikeIgnoreCase_ReturnsTwo() {
        // setup
        String name = "Grand Hotel";
        Hotel hotel = Hotel.builder()
                .id(UUID.randomUUID())
                .name(name)
                .address("London, England")
                .hotelType("Hotel")
                .city("London")
                .distance("505")
                .title("5 star hotel")
                .rooms(new ArrayList<>())
                .photos(new ArrayList<>())
                .cheapestPrice(400)
                .featured(true)
                .description("hello")
                .createdAt(LocalDateTime.now())
                .build();
        hotelDao.save(hotel);

        Hotel hotel2 = Hotel.builder()
                .id(UUID.randomUUID())
                .name("Best hotel")
                .address("Cambridge, England")
                .hotelType("Hotel")
                .city("London")
                .distance("505")
                .title("Best 5 star hotel")
                .rooms(new ArrayList<>())
                .photos(new ArrayList<>())
                .cheapestPrice(500)
                .featured(true)
                .description("hello")
                .createdAt(LocalDateTime.now())
                .build();
        hotelDao.save(hotel2);

        // exec
        List<Hotel> hotels = hotelDao.findByCityIsLikeIgnoreCase("londoN");

        // assert
        assertThat(hotels.size()).isEqualTo(2);
    }

    @Test
    void HotelDao_FindByCityIsLikeIgnoreCaseAndCheapestPriceBetween_ReturnsZero() {
        // setup
        String name = "Grand Hotel";
        Hotel hotel = Hotel.builder()
                .id(UUID.randomUUID())
                .name(name)
                .address("London, England")
                .hotelType("Hotel")
                .city("London")
                .distance("505")
                .title("5 star hotel")
                .rooms(new ArrayList<>())
                .photos(new ArrayList<>())
                .cheapestPrice(400)
                .featured(true)
                .description("hello")
                .createdAt(LocalDateTime.now())
                .build();
        hotelDao.save(hotel);

        Hotel hotel2 = Hotel.builder()
                .id(UUID.randomUUID())
                .name("Best hotel")
                .address("Cambridge, England")
                .hotelType("Hotel")
                .city("London")
                .distance("505")
                .title("Best 5 star hotel")
                .rooms(new ArrayList<>())
                .photos(new ArrayList<>())
                .cheapestPrice(500)
                .featured(true)
                .description("hello")
                .createdAt(LocalDateTime.now())
                .build();
        hotelDao.save(hotel2);

        // exec
        List<Hotel> hotels =
                hotelDao.findByCityIsLikeIgnoreCaseAndCheapestPriceBetween("London", 600.0, 800.0);

        // assert
        assertThat(hotels.size()).isEqualTo(0);
    }
}
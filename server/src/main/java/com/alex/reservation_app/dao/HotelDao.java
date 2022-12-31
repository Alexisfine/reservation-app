package com.alex.reservation_app.dao;

import com.alex.reservation_app.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotelDao extends JpaRepository<Hotel, UUID> {
    public boolean existsByName(String name);

    @Query("select count(hotels) from Hotel hotels where lower(hotels.city) like lower(concat('%', concat(?1, '%')))")
    Integer countByCitiesKeyWord(String city);

    Integer countByHotelTypeLike(String hotelType);

    List<Hotel> findByFeaturedAndCheapestPriceBetween(Boolean featured, Integer min, Integer max);

    List<Hotel> findByCityIsLikeIgnoreCase(String city);

    List<Hotel> findByCityIsLikeIgnoreCaseAndCheapestPriceBetween(String city, Double min, Double max);
}

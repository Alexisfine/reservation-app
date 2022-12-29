package com.alex.reservation_app.service;

import com.alex.reservation_app.dao.HotelDao;
import com.alex.reservation_app.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    private HotelDao hotelDao;

    @Autowired
    public HotelService(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    public Hotel addHotel(Hotel newHotel) {
        return hotelDao.save(newHotel);
    }
}

package com.alex.reservation_app.service.impl;

import com.alex.reservation_app.dao.HotelDao;
import com.alex.reservation_app.dto.HotelDto;
import com.alex.reservation_app.exception.HotelNotFoundException;
import com.alex.reservation_app.model.Hotel;
import com.alex.reservation_app.service.HotelService;
import com.alex.reservation_app.utils.UpdateColumnUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    private HotelDao hotelDao;

    @Autowired
    public HotelServiceImpl(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    public Hotel addHotel(Hotel newHotel) {
        if (hotelDao.existsByName(newHotel.getName())) {
            throw new IllegalArgumentException(newHotel.getName() + " already exists");
        }
        return hotelDao.save(newHotel);
    }

    @Override
    public Hotel updateHotel(HotelDto hotelDto, UUID id) {
        Hotel hotel = hotelDao
                .findById(id)
                .orElseThrow(
                    () -> new HotelNotFoundException("Cannot find hotel with id " + id)
                );

        BeanUtils.copyProperties(hotelDto, hotel, UpdateColumnUtil.getNullPropertyNames(hotelDto));

        return hotelDao.save(hotel);
    }

    @Override
    public Hotel getHotelById(UUID id) {
        Hotel hotel = hotelDao
                .findById(id)
                .orElseThrow(
                        () -> new HotelNotFoundException("Cannot find hotel with id " + id)
                );
        return hotel;
    }

    @Override
    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = hotelDao.findAll();
        return hotels;
    }

    @Override
    public String deleteHotel(UUID id) {
        Hotel hotel = hotelDao
                .findById(id)
                .orElseThrow(
                        () -> new HotelNotFoundException("Cannot find hotel with id " + id)
                );
        hotelDao.deleteById(id);
        return "Hotel " + id + " has been deleted";
    }
}

package com.alex.reservation_app.service;

import com.alex.reservation_app.dao.HotelDao;
import com.alex.reservation_app.dto.HotelDto;
import com.alex.reservation_app.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.UUID;

@Service
public interface HotelService {

    Hotel addHotel(Hotel newHotel);

    Hotel updateHotel(HotelDto hotelDto, UUID id);

    Hotel getHotelById(UUID id);

    List<Hotel> getAllHotels();

    String deleteHotel(UUID id);

    List<Integer>  countByCity(String[] cityList);


    List<Integer>  countByType(String[] typeList);

    List<Hotel> getByFeatured(boolean parseBoolean, Integer limit);
}

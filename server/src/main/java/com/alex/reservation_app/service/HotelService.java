package com.alex.reservation_app.service;

import com.alex.reservation_app.dao.HotelDao;
import com.alex.reservation_app.dto.HotelDto;
import com.alex.reservation_app.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.UUID;

@Service
public interface HotelService {


    public Hotel addHotel(Hotel newHotel);

    public Hotel updateHotel(HotelDto hotelDto, UUID id);
}

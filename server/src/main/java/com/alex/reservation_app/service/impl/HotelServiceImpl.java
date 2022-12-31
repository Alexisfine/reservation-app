package com.alex.reservation_app.service.impl;

import com.alex.reservation_app.dao.HotelDao;
import com.alex.reservation_app.dto.HotelDto;
import com.alex.reservation_app.dto.RoomDto;
import com.alex.reservation_app.exception.HotelNotFoundException;
import com.alex.reservation_app.model.Hotel;
import com.alex.reservation_app.model.Room;
import com.alex.reservation_app.service.HotelService;
import com.alex.reservation_app.utils.UpdateColumnUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    private HotelDao hotelDao;

    @Autowired
    public HotelServiceImpl(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    public HotelDto addHotel(Hotel newHotel) {
        if (hotelDao.existsByName(newHotel.getName())) {
            throw new IllegalArgumentException(newHotel.getName() + " already exists");
        }
        Hotel savedHotel = hotelDao.save(newHotel);
        return mapHotelToHotelDto(savedHotel);
    }

    @Override
    public HotelDto updateHotel(HotelDto hotelDto, UUID id) {
        Hotel hotel = hotelDao
                .findById(id)
                .orElseThrow(
                    () -> new HotelNotFoundException("Cannot find hotel with id " + id)
                );

        BeanUtils.copyProperties(hotelDto, hotel, UpdateColumnUtil.getNullPropertyNames(hotelDto));

        Hotel savedHotel = hotelDao.save(hotel);

        return mapHotelToHotelDto(savedHotel);

    }


    @Override
    public HotelDto getHotelById(UUID id) {
        Hotel hotel = hotelDao
                .findById(id)
                .orElseThrow(
                        () -> new HotelNotFoundException("Cannot find hotel with id " + id)
                );
        return mapHotelToHotelDto(hotel);
    }

    @Override
    public List<HotelDto> getAllHotels() {
        List<HotelDto> hotelList = hotelDao
                .findAll()
                .stream()
                .map(hotel -> mapHotelToHotelDto(hotel))
                .collect(Collectors.toList());
        return hotelList;
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

    @Override
    public List<Integer> countByCity(String[] cityList) {
        return Arrays
                .stream(cityList)
                .map(city -> hotelDao.countByCitiesKeyWord(city))
                .toList();
    }

    @Override
    public List<Integer> countByType(String[] typeList) {
        return Arrays
                .stream(typeList)
                .map(type -> hotelDao.countByHotelTypeLike(type))
                .collect(Collectors.toList());

    }

    @Override
    public List<HotelDto> getByFeatured(boolean parseBoolean, Integer limit, Integer max, Integer min) {
        List<Hotel> hotelList = hotelDao.findByFeaturedAndCheapestPriceBetween(parseBoolean, min, max);
        if (hotelList.size() > limit) {
            hotelList = hotelList.subList(0, limit);
        }

        return mapHotelListToHotelDtoList(hotelList);
    }



    @Override
    public List<HotelDto> findByCity(String city) {
        List<Hotel> hotelList = hotelDao.findByCityIsLikeIgnoreCase(city);
        return mapHotelListToHotelDtoList(hotelList);
    }

    @Override
    public List<HotelDto> findByCityAnd(String city, String max, String min) {
        List<Hotel> hotelList = hotelDao
                .findByCityIsLikeIgnoreCaseAndCheapestPriceBetween(
                        city,
                        Double.parseDouble(min),
                        Double.parseDouble(max));
        return mapHotelListToHotelDtoList(hotelList);
    }

    private HotelDto mapHotelToHotelDto(Hotel savedHotel) {
        HotelDto returnHotel = new HotelDto(
                savedHotel.getId(),
                savedHotel.getName(),
                savedHotel.getHotelType(),
                savedHotel.getCity(),
                savedHotel.getAddress(),
                savedHotel.getDistance(),
                savedHotel.getDescription(),
                savedHotel.getTitle(),
                savedHotel.getRating(),
                savedHotel.getCheapestPrice(),
                savedHotel.getFeatured(),
                savedHotel.getCreatedAt(),
                savedHotel.getUpdated_at(),
                mapRoomListToRoomDtoList(savedHotel.getRooms())
        );
        return returnHotel;
    }

    private List<HotelDto> mapHotelListToHotelDtoList(List<Hotel> hotelList) {
        return hotelList
                .stream()
                .map(hotel -> mapHotelToHotelDto(hotel))
                .collect(Collectors.toList());
    }

    private  RoomDto mapRoomToRoomDto(Room room) {
        RoomDto returnRoom = new RoomDto(
                room.getId(),
                room.getHotel().getId(),
                room.getTitle(),
                room.getRoomNumber(),
                room.getPrice(),
                room.getDescription(),
                room.getMaxPeople(),
                room.getRoomType(),
                room.getUnavailableDates()
        );
        return returnRoom;
    }

    private  List<RoomDto> mapRoomListToRoomDtoList(List<Room> roomList) {
        return roomList
                .stream()
                .map(room -> mapRoomToRoomDto(room))
                .collect(Collectors.toList());
    }
}

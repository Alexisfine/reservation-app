package com.alex.reservation_app.mapper;

import com.alex.reservation_app.dto.HotelDto;
import com.alex.reservation_app.model.Hotel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateHotelFromDto(HotelDto dto, @MappingTarget Hotel entity);
}

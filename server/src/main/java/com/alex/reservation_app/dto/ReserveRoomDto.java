package com.alex.reservation_app.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ReserveRoomDto {
    private Long startDate;
    private Long endDate;

    public ReserveRoomDto() {
    }

    public ReserveRoomDto(Long startDate, Long endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }



    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ReserveRoomDto{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

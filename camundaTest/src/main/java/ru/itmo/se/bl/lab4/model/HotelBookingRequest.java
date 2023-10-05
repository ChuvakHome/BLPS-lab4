package ru.itmo.se.bl.lab4.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.se.bl.lab4.entity.TouristInfo;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class HotelBookingRequest {
    private int hotelId;
    private int days;
    private Date bookingDate;
    private List<TouristInfo> touristInfoList;
}

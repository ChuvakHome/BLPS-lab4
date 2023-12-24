package ru.itmo.se.bl.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.se.bl.lab4.entity.Hotel;
import ru.itmo.se.bl.lab4.entity.HotelBooking;
import ru.itmo.se.bl.lab4.entity.TouristInfo;
import ru.itmo.se.bl.lab4.exception.HotelNotFoundException;
import ru.itmo.se.bl.lab4.model.HotelBookingRequest;
import ru.itmo.se.bl.lab4.repository.HotelBookingRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelBookingService {
    private final HotelService hotelService;
    private final HotelBookingRepository repo;

    @Autowired
    public HotelBookingService(HotelService hotelService, HotelBookingRepository repo) {
        this.hotelService = hotelService;
        this.repo = repo;
    }

    public List<HotelBooking> getBookingsByDayDifference(Date date, int days) {
        return repo.findBookingsByDayDifference(date, days);
    }

    public void addBooking(HotelBookingRequest bookingRequest) throws HotelNotFoundException {
        try {
            int hotelId = bookingRequest.getHotelId();
            Hotel hotel = hotelService.getById(hotelId);

            List<HotelBooking> bookings = new ArrayList<>();

            for (TouristInfo info: bookingRequest.getTouristInfoList()) {
                HotelBooking hotelBooking = new HotelBooking();
                hotelBooking.setId(null);
                hotelBooking.setHotel(hotel);
                hotelBooking.setBookingDate(bookingRequest.getBookingDate());
                hotelBooking.setDays(bookingRequest.getDays());
                hotelBooking.setTouristInfo(info);

                bookings.add(hotelBooking);
            }

            repo.saveAll(bookings);
        } catch (HotelNotFoundException e) {
            throw e;
        }
    }
}

package ru.itmo.se.bl.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itmo.se.bl.lab4.entity.HotelBooking;
import ru.itmo.se.bl.lab4.entity.TouristInfo;

import java.sql.Date;
import java.util.List;

@Repository
public interface HotelBookingRepository extends JpaRepository<HotelBooking, Integer> {
    HotelBooking findByTouristInfo(TouristInfo touristInfo);

    @Query(
            value = "SELECT * FROM hotel_booking WHERE EXTRACT(DAY FROM booking_date - CAST(:date AS timestamp)) = :days",
            nativeQuery = true)
    List<HotelBooking> findBookingsByDayDifference(@Param("date") Date date, @Param("days") int days);
}

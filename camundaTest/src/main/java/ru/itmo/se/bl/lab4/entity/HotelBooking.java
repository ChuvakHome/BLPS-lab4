package ru.itmo.se.bl.lab4.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.sql.Date;

@Entity
@Table(name = "hotel_booking")
@Data
@Getter
public class HotelBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "booking_date", nullable = false)
    private Date bookingDate;

    @Column(nullable = false)
    private int days;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tourist_info_id", nullable = false)
    private TouristInfo touristInfo;
}

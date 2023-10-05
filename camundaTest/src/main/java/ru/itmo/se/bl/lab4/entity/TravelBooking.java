package ru.itmo.se.bl.lab4.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "travel_bookings")
@Data
@Getter
public class TravelBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "travel_id", nullable = false)
    private Travel travel;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tourist_info_id", nullable = false)
    private TouristInfo touristInfo;
}

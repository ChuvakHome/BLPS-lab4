package ru.itmo.se.bl.lab4.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.sql.Date;

@Entity
@Table(name = "bookings")
@Data
@Getter
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "booking_date", nullable = false)
	private Date bookingDate;
	
	@Column(nullable = false)
	private int days;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "tourist_info_id", nullable = false)
	private TouristInfo touristInfo;
}

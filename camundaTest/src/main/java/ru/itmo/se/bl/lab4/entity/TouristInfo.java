package ru.itmo.se.bl.lab4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tourist_info")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
public class TouristInfo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "passport_id")
	private Passport passport;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "international_passport_id")
	private InternationalPassport internationalPassport;

	@Column
	private String email;
}

package ru.itmo.se.bl.lab4.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TouristInfoDTO implements Serializable {
    private PassportDTO passport;
    private InternationalPassportDTO internationalPassport;
    private String email;
}

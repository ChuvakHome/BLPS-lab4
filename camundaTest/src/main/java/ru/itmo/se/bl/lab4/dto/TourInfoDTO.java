package ru.itmo.se.bl.lab4.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class TourInfoDTO implements Serializable {
    @NotNull
    private List<TouristInfoDTO> touristList;
    private Date bookDate;
    private int bookDays;
}

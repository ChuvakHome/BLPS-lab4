package ru.itmo.se.bl.lab4.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.se.bl.lab4.entity.TouristInfo;

import java.util.List;

@NoArgsConstructor
@Data
public class TravelBookingRequest {
    private int travelId;

    private List<TouristInfo> touristInfoList;
}

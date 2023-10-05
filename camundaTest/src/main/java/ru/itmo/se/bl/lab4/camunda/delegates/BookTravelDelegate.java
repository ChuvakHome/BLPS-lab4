package ru.itmo.se.bl.lab4.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.se.bl.lab4.dto.TourInfoDTO;
import ru.itmo.se.bl.lab4.entity.TouristInfo;
import ru.itmo.se.bl.lab4.model.TravelBookingRequest;
import ru.itmo.se.bl.lab4.service.TravelBookingService;
import ru.itmo.se.bl.lab4.utils.Converters;

import java.util.List;

@Component
public class BookTravelDelegate implements JavaDelegate {
    @Autowired
    private TravelBookingService travelBookingService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TourInfoDTO tourInfoDTO = (TourInfoDTO) execution.getVariable("tourInfo");

        List<TouristInfo> touristList = Converters.TouristInfoConverter.dtosToEntityList(tourInfoDTO.getTouristList());

        TravelBookingRequest travelBookingRequest = new TravelBookingRequest();
        travelBookingRequest.setTouristInfoList(touristList);

        Integer travelId = (Integer) execution.getVariable("travelId");
        travelBookingRequest.setTravelId(travelId);

        travelBookingService.addBooking(travelBookingRequest);
    }
}

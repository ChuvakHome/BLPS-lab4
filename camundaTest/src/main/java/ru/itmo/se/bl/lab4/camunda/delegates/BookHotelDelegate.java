package ru.itmo.se.bl.lab4.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.se.bl.lab4.dto.TourInfoDTO;
import ru.itmo.se.bl.lab4.entity.TouristInfo;
import ru.itmo.se.bl.lab4.model.HotelBookingRequest;
import ru.itmo.se.bl.lab4.service.HotelBookingService;
import ru.itmo.se.bl.lab4.service.TourService;
import ru.itmo.se.bl.lab4.utils.Converters;

import java.util.List;

@Component
public class BookHotelDelegate implements JavaDelegate {
    @Autowired
    private TourService tourService;

    @Autowired
    private HotelBookingService hotelBookingService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TourInfoDTO tourInfoDTO = (TourInfoDTO) execution.getVariable("tourInfo");

        List<TouristInfo> touristList = Converters.TouristInfoConverter.dtosToEntityList(tourInfoDTO.getTouristList());

        HotelBookingRequest hotelBookingRequest = new HotelBookingRequest();
        hotelBookingRequest.setTouristInfoList(touristList);

        Integer tourId = (Integer) execution.getVariable("tourId");
        Integer hotelId = tourService.getById(tourId).getTourHotel().getId();
        hotelBookingRequest.setHotelId(hotelId);

        hotelBookingRequest.setBookingDate(tourInfoDTO.getBookDate());
        hotelBookingRequest.setDays(tourInfoDTO.getBookDays());

        hotelBookingService.addBooking(hotelBookingRequest);
    }
}

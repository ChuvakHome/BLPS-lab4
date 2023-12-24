package ru.itmo.se.bl.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.se.bl.lab4.entity.TouristInfo;
import ru.itmo.se.bl.lab4.entity.Travel;
import ru.itmo.se.bl.lab4.entity.TravelBooking;
import ru.itmo.se.bl.lab4.exception.TravelNotFoundException;
import ru.itmo.se.bl.lab4.model.TravelBookingRequest;
import ru.itmo.se.bl.lab4.repository.TravelBookingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TravelBookingService {
    private final TravelService travelService;
    private final TravelBookingRepository repo;

    @Autowired
    public TravelBookingService(TravelService travelService, TravelBookingRepository repo) {
        this.travelService = travelService;
        this.repo = repo;
    }

    public TravelBooking getById(Integer id) {
        return this.repo.findById(id).orElse(null);
    }

    public TravelBooking getByTouristInfo(TouristInfo touristInfo) {
        return this.repo.findByTouristInfo(touristInfo);
    }

    public void saveTravelBooking(TravelBooking travelBooking) {
        repo.save(travelBooking);
    }

    @Transactional
    public void addBooking(TravelBookingRequest bookingRequest) throws TravelNotFoundException {
        int travelId = bookingRequest.getTravelId();
        Travel travel = travelId < 0 ? null : travelService.getById(travelId);

        List<TravelBooking> bookings = new ArrayList<>();

        for (TouristInfo info: bookingRequest.getTouristInfoList()) {
            if (travel != null) {
                TravelBooking travelBooking = new TravelBooking();
                travelBooking.setId(null);
                travelBooking.setTravel(travel);
                travelBooking.setTouristInfo(info);

                bookings.add(travelBooking);
            }
        }

        repo.saveAll(bookings);
    }
}

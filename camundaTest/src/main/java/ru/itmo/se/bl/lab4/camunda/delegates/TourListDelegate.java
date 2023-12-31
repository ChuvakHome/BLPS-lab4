package ru.itmo.se.bl.lab4.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.se.bl.lab4.service.TourService;

import java.util.HashMap;
import java.util.Map;

@Component
public class TourListDelegate implements JavaDelegate {
    private final TourService tourService;

    @Autowired
    public TourListDelegate(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<Integer, String> tours = new HashMap<>();

        delegateExecution.removeVariable("citiesList");

        Integer cityId = (Integer) delegateExecution.getVariable("cityId");

        tourService.getByTourCityId(cityId).forEach(t -> tours.put(t.getId(), t.getTourName()));

        delegateExecution.setVariable("touristsCount", 0);
        delegateExecution.setVariable("toursList", Variables
                .objectValue(tours)
                .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                .create());
    }
}

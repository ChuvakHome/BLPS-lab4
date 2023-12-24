package ru.itmo.se.bl.lab4.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.se.bl.lab4.service.TravelService;

import java.util.HashMap;
import java.util.Map;

@Component
public class TravelListDelegate implements JavaDelegate {
    private final TravelService travelService;

    @Autowired
    public TravelListDelegate(TravelService travelService) {
        this.travelService = travelService;
    }

    public void execute(DelegateExecution execution) throws Exception {
        Integer cityId = (Integer) execution.getVariable("cityId");
        Map<Integer, String> travels = new HashMap<>();
        travelService.getByEndCityId(cityId).forEach(t -> travels.put(t.getId(),
                String.format("Дата: %td.%tm.%tY\nДлительность (в минутах): %d",
                        t.getTravelDate(), t.getTravelDate(), t.getTravelDate(), t.getTravelDuration())));

        execution.setVariable("travelsList", Variables
                .objectValue(travels)
                .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                .create());
    }
}

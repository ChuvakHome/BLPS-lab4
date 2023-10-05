package ru.itmo.se.bl.lab4.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.se.bl.lab4.service.CityService;

import java.util.HashMap;
import java.util.Map;

@Component
public class CityListDelegate implements JavaDelegate {
    @Autowired
    private CityService cityService;

    public void execute(DelegateExecution execution) throws Exception {
        Map<Integer, String> cities = new HashMap<>();

        cityService.getAll().forEach(c -> cities.put(c.getId(), c.getLocalName()));

        execution.setVariable("citiesList", Variables
                .objectValue(cities)
                .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                .create());
    }
}

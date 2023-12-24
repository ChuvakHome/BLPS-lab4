package ru.itmo.se.bl.lab4.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itmo.se.bl.lab4.dto.TourInfoDTO;
import ru.itmo.se.bl.lab4.dto.TouristInfoDTO;
import ru.itmo.se.bl.lab4.model.EmailNotification;
import ru.itmo.se.bl.lab4.service.MessageService;

@Component
public class SendEmailDelegate implements JavaDelegate {
    @Value("${messaging.notification.queue}")
    private String queueName;

    private final MessageService messageService;

    @Autowired
    public SendEmailDelegate(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution.removeVariable("toursList");

        TourInfoDTO tourInfoDTO = (TourInfoDTO) execution.getVariable("tourInfo");

        for (TouristInfoDTO info: tourInfoDTO.getTouristList()) {
            EmailNotification emailNotification = new EmailNotification();

            if (info.getPassport() != null) {
                emailNotification.setFirstName(info.getPassport().getFirstName());
                emailNotification.setLastName(info.getPassport().getLastName());
                emailNotification.setMiddleName(info.getPassport().getMiddleName());
            } else {
                emailNotification.setFirstName(info.getInternationalPassport().getFirstName());
                emailNotification.setLastName(info.getInternationalPassport().getLastName());
                emailNotification.setMiddleName(null);
            }

            emailNotification.setEmail(info.getEmail());
            emailNotification.setBookingDate(tourInfoDTO.getBookDate());
            emailNotification.setBookDays(tourInfoDTO.getBookDays());

            messageService.sendObjectMessage(queueName, emailNotification);
        }
    }
}

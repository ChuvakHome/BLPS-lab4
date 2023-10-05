package ru.itmo.se.bl.lab4.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itmo.se.bl.lab4.entity.HotelBooking;
import ru.itmo.se.bl.lab4.entity.TouristInfo;
import ru.itmo.se.bl.lab4.model.EmailReminderNotification;
import ru.itmo.se.bl.lab4.service.HotelBookingService;
import ru.itmo.se.bl.lab4.service.MessageService;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class TimerDelegate implements JavaDelegate {
    @Value("${messaging.reminding.queue}")
    private String queueName;

    @Autowired
    private HotelBookingService hotelBookingService;

    @Autowired
    private MessageService messageService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Date date = Date.valueOf(LocalDate.now());

        for (HotelBooking booking: hotelBookingService.getBookingsByDayDifference(date, 2)) {
            TouristInfo touristInfo = booking.getTouristInfo();

            if (touristInfo != null && touristInfo.getEmail() != null) {
                EmailReminderNotification notification = new EmailReminderNotification();

                notification.setEmail(touristInfo.getEmail());

                if (touristInfo.getPassport() != null) {
                    notification.setLastName(touristInfo.getPassport().getLastName());
                    notification.setFirstName(touristInfo.getPassport().getFirstName());
                    notification.setMiddleName(touristInfo.getPassport().getMiddleName());
                }
                else if (touristInfo.getInternationalPassport() != null) {
                    notification.setFirstName(touristInfo.getInternationalPassport().getFirstName());
                    notification.setLastName(touristInfo.getInternationalPassport().getLastName());
                }

                notification.setBookingDate(booking.getBookingDate());
                notification.setBookDays(booking.getDays());

                messageService.sendObjectMessage(queueName, notification);
            }
        }
    }
}

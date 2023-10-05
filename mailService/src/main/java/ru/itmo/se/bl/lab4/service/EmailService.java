package ru.itmo.se.bl.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.itmo.se.bl.lab4.data.EmailDetails;
import ru.itmo.se.bl.lab4.model.EmailNotification;
import ru.itmo.se.bl.lab4.model.EmailReminderNotification;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendEmail(EmailDetails details) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(details.getRecipient());
        mailMessage.setText(details.getMsgBody());
        mailMessage.setSubject(details.getSubject());
        javaMailSender.send(mailMessage);
    }

    @JmsListener(destination = "notification.q")
    public void onReceiveNotification(ObjectMessage objectMessage) {
        try {
            if (objectMessage.getObject() instanceof EmailNotification notification) {
                EmailDetails details = new EmailDetails();
                details.setSubject("Уведомление от Туристер.ру");
                details.setRecipient(notification.getEmail());
                details.setMsgBody(notification.getMiddleName() != null ?
                        String.format("Здравствуйте, %s %s %s! Бронирование на %td.%tm.%tY длительностью %d дней завершено успешно.",
                                notification.getLastName(), notification.getFirstName(), notification.getMiddleName(),
                                notification.getBookingDate(), notification.getBookingDate(), notification.getBookingDate(), notification.getBookDays()) :
                        String.format("Здравствуйте, %s %s! Бронирование на %td.%tm.%tY длительностью %d дней завершено успешно.",
                                notification.getLastName(), notification.getFirstName(),
                                notification.getBookingDate(), notification.getBookingDate(), notification.getBookingDate(), notification.getBookDays()));

                sendEmail(details);
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    @JmsListener(destination = "reminding.q")
    public void onReceiveReminderNotification(ObjectMessage objectMessage) {
        try {
            if (objectMessage.getObject() instanceof EmailReminderNotification notification) {
                if (notification.getEmail() != null) {
                    EmailDetails details = new EmailDetails();
                    details.setSubject("Напоминание от Туристер.ру");
                    details.setRecipient(notification.getEmail());
                    details.setMsgBody(notification.getMiddleName() != null ?
                            String.format("Здравствуйте, %s %s %s! " +
                                            "Напоминаем Вам, что на %td.%tm.%tY забронирован отель на %d дней.",
                                    notification.getLastName(), notification.getFirstName(), notification.getMiddleName(),
                                    notification.getBookingDate(), notification.getBookingDate(), notification.getBookingDate(), notification.getBookDays()) :
                            String.format("Здравствуйте, %s %s! " +
                                            "Напоминаем Вам, что на %td.%tm.%tY забронирован отель на %d дней.",
                                    notification.getLastName(), notification.getFirstName(),
                                    notification.getBookingDate(), notification.getBookingDate(), notification.getBookingDate(), notification.getBookDays()));

                    sendEmail(details);
                }
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

//    @Scheduled(cron = "0 0 0 * * *")
//    public void sendNotificationEmails() {
//        Date date = Date.valueOf(LocalDate.now());
//
//        for (Booking booking: bookingService.getBookingsByDayDifference(date, 2)) {
//            TouristInfo touristInfo = booking.getTouristInfo();
//
//            if (touristInfo != null && touristInfo.getEmail() != null) {
//                EmailDetails details = new EmailDetails();
//                details.setRecipient(touristInfo.getEmail());
//                details.setSubject("Напоминание от Туристер.ру");
//
//                String firstName, lastName;
//
//                if (touristInfo.getPassport() != null) {
//                    firstName = touristInfo.getPassport().getFirstName();
//                    lastName = touristInfo.getPassport().getLastName();
//                    String middleName = touristInfo.getPassport().getMiddleName();
//
//                    details.setMsgBody(String.format("Здравствуйте, %s %s %s! " +
//                                    "Напоминаем Вам, что на %td.%tm.%tY забронирован вылет.",
//                            lastName, firstName, middleName, booking.getBookingDate(), booking.getBookingDate(), booking.getBookingDate()));
//                } else if (touristInfo.getInternationalPassport() != null) {
//                    firstName = touristInfo.getInternationalPassport().getFirstName();
//                    lastName = touristInfo.getInternationalPassport().getLastName();
//
//                    details.setMsgBody(String.format("Здравствуйте, %s %s! " +
//                                    "Напоминаем Вам, что на %td.%tm.%tY забронирован вылет.",
//                            firstName, lastName, booking.getBookingDate(), booking.getBookingDate(), booking.getBookingDate()));
//                } else
//                    continue;
//
//                sendEmail(details);
//            }
//        }
//    }
}

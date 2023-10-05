package ru.itmo.se.bl.lab4.camunda.delegates;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.se.bl.lab4.dto.TourInfoDTO;
import ru.itmo.se.bl.lab4.exception.PaymentException;
import ru.itmo.se.bl.lab4.model.CardInfo;
import ru.itmo.se.bl.lab4.model.PaymentRequest;
import ru.itmo.se.bl.lab4.service.PaymentService;
import ru.itmo.se.bl.lab4.service.TourService;
import ru.itmo.se.bl.lab4.service.TravelService;

import java.sql.Date;

@Component
public class PaymentDelegate implements JavaDelegate {
    @Autowired
    private TravelService travelService;

    @Autowired
    private TourService tourService;

    @Autowired
    private PaymentService paymentService;

    private static CardInfo getCardInfo(DelegateExecution delegateExecution) {
        String cardNumber = (String) delegateExecution.getVariable("ccNumber");
        String cardOwner = (String) delegateExecution.getVariable("ccOwner");
        int csc = (Integer) delegateExecution.getVariable("ccCSC");

        int expMonth = (Integer) delegateExecution.getVariable("ccExpireMonth");
        int expYear = (Integer) delegateExecution.getVariable("ccExpireYear");

        return CardInfo.builder()
                .cardNumber(cardNumber)
                .cardOwner(cardOwner)
                .expireDate(Date.valueOf(String.format("20%d-%d-01", expYear, expMonth)))
                .csc(csc)
                .build();
    }

    public void execute(DelegateExecution execution) throws Exception {
        TourInfoDTO tourInfoDTO = (TourInfoDTO) execution.getVariable("tourInfo");

        Integer travelId = (Integer) execution.getVariable("travelId");
        int travelCost = travelId < 0 ? 0 : travelService.getById(travelId).getCost();

        Integer tourId = (Integer) execution.getVariable("tourId");
        int charge = travelCost + tourInfoDTO.getTouristList().size() * tourService.getById(tourId).getTourHotel().getNightCost();

        CardInfo cardInfo = getCardInfo(execution);

        try {
            paymentService.doPayment(new PaymentRequest(cardInfo, charge));
        } catch (PaymentException e) {
            String errorMessage;

            switch (e.getPaymentResult()) {
                case CARD_BLOCKED -> errorMessage = "Карта заблокирована.";
                case CARD_EXPIRED -> errorMessage = "Истек срок действия карты.";
                case NOT_ENOUGH_MONEY -> errorMessage = "На карте недостаточно средств.";
                case CONNECTION_UNESTABLISHED_PROBLEM -> errorMessage = "Невозможно установить соединение с банком.";
                default -> errorMessage = "";
            }

//            execution.setVariable("paymentErrorMessage", errorMessage);

            throw new BpmnError("PAYMENT_ERROR", errorMessage);
        }
    }
}

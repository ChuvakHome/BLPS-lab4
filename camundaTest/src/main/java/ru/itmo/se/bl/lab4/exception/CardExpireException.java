package ru.itmo.se.bl.lab4.exception;

import ru.itmo.se.bl.lab4.model.PaymentResult;

public class CardExpireException extends PaymentException {
    public CardExpireException() {
        super(PaymentResult.CARD_EXPIRED);
    }
}

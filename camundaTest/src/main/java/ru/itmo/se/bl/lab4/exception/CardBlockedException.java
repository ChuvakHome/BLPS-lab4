package ru.itmo.se.bl.lab4.exception;

import ru.itmo.se.bl.lab4.model.PaymentResult;

public class CardBlockedException extends PaymentException {
    public CardBlockedException() {
        super(PaymentResult.CARD_BLOCKED);
    }
}

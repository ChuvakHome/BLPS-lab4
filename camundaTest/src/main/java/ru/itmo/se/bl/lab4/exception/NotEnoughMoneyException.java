package ru.itmo.se.bl.lab4.exception;

import ru.itmo.se.bl.lab4.model.PaymentResult;

public class NotEnoughMoneyException extends PaymentException {
    public NotEnoughMoneyException() {
        super(PaymentResult.NOT_ENOUGH_MONEY);
    }
}

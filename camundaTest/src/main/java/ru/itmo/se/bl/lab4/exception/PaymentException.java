package ru.itmo.se.bl.lab4.exception;

import ru.itmo.se.bl.lab4.model.PaymentResult;

public class PaymentException extends Exception {
    private final PaymentResult paymentResult;

    public PaymentException(PaymentResult result) {
        super(result.getMessage());
        this.paymentResult = result;
    }

    public PaymentResult getPaymentResult() {
        return paymentResult;
    }
}

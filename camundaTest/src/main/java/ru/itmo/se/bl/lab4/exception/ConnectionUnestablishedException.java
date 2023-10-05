package ru.itmo.se.bl.lab4.exception;

import ru.itmo.se.bl.lab4.model.PaymentResult;

public class ConnectionUnestablishedException extends PaymentException {
    public ConnectionUnestablishedException() {
        super(PaymentResult.CONNECTION_UNESTABLISHED_PROBLEM);
    }
}

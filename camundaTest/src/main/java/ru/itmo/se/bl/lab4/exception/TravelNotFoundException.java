package ru.itmo.se.bl.lab4.exception;

public class TravelNotFoundException extends Exception {
    public TravelNotFoundException(int id) {
        super("Travel with id: " + id + " cannot be found");
    }
}

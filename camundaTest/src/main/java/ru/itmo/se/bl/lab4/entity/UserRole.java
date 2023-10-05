package ru.itmo.se.bl.lab4.entity;

public enum UserRole {
    USER,
    ADMIN
    ;

    public String getAsAuthority() {
        return "ROLE_" + name();
    }
}

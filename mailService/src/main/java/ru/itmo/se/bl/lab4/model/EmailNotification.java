package ru.itmo.se.bl.lab4.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@Data
public class EmailNotification implements Serializable {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Nullable
    private String middleName;

    @NotNull
    private String email;

    private int bookDays;

    @NotNull
    private Date bookingDate;
}


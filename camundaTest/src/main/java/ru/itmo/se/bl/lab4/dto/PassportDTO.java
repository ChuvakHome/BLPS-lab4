package ru.itmo.se.bl.lab4.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.se.bl.lab4.entity.Gender;
import ru.itmo.se.bl.lab4.entity.Passport;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@Data
public class PassportDTO implements Serializable {
    @NotEmpty(message = "Last name is mandatory")
    private String lastName;

    @NotEmpty(message = "First name is mandatory")
    private String firstName;
    private String middleName;

    @NotNull(message = "Birth date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthDate;

    @NotNull(message = "Issue date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String issueDate;

    @NotEmpty(message = "Series is mandatory")
    private String series;

    @NotEmpty(message = "Number is mandatory")
    private String number;

    @NotNull(message = "Gender is mandatory")
    private String gender;

    public Passport toEntity() {
        Gender gender;

        try {
            gender = Gender.valueOf(this.gender);
        } catch (IllegalArgumentException e) {
            gender = null;
        }

        Date birthDate = Date.valueOf(LocalDate.parse(this.birthDate));
        Date issueDate = Date.valueOf(LocalDate.parse(this.issueDate));

        Passport passport = new Passport();
        passport.setId(null);
        passport.setSeries(series);
        passport.setNumber(number);
        passport.setLastName(lastName);
        passport.setFirstName(firstName);
        passport.setMiddleName(middleName);
        passport.setBirthDate(birthDate);
        passport.setIssueDate(issueDate);
        passport.setGender(gender);

        return passport;
    }

}

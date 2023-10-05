package ru.itmo.se.bl.lab4.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.se.bl.lab4.entity.Gender;
import ru.itmo.se.bl.lab4.entity.InternationalPassport;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class InternationalPassportDTO {
    @NotEmpty(message = "Last name is mandatory")
    private String lastName;

    @NotEmpty(message = "First name is mandatory")
    private String firstName;

    @NotEmpty(message = "Series is mandatory")
    private String series;

    @NotEmpty(message = "Number is mandatory")
    private String number;

    @NotNull(message = "Birth date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthDate;

    @NotNull(message = "Expire date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String expireDate;

    @NotNull(message = "Gender is mandatory")
    private String gender;

    @NotEmpty(message = "Citizenship is mandatory")
    private String citizenship;

    public InternationalPassport toEntity() {
        Gender gender;

        try {
            gender = Gender.valueOf(this.gender);
        } catch (IllegalArgumentException e) {
            gender = null;
        }

        Date birthDate = Date.valueOf(LocalDate.parse(this.birthDate));
        Date expireDate = Date.valueOf(LocalDate.parse(this.expireDate));

        InternationalPassport internationalPassport = new InternationalPassport();
        internationalPassport.setId(null);
        internationalPassport.setLastName(lastName);
        internationalPassport.setFirstName(firstName);
        internationalPassport.setSeries(series);
        internationalPassport.setNumber(number);
        internationalPassport.setBirthDate(birthDate);
        internationalPassport.setExpireDate(expireDate);
        internationalPassport.setCitizenship(citizenship);
        internationalPassport.setGender(gender);

        return internationalPassport;
    }

}

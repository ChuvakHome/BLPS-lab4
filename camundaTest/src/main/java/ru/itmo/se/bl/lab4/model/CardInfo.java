package ru.itmo.se.bl.lab4.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
@Builder
public class CardInfo {
	@NotEmpty(message = "Expire date is mandatory")
	private Date expireDate;
	
	@NotEmpty(message = "Card Holder is mandatory")
	private String cardOwner;
	
	@NotEmpty(message = "Card Number is mandatory")
	private String cardNumber;
	
	@NotEmpty(message = "CSC is mandatory")
	private int csc;
}

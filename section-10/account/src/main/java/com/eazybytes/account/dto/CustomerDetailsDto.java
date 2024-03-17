package com.eazybytes.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "customerDetails",
        description = "Schema to hold customer, accounts, cards and loans information"
)
public class CustomerDetailsDto {

    @Schema(
            description = "name of the customer", example = "Eazy Bytes"
    )
    @NotEmpty(message = "name can not be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name is in between 5 and 30")
    private String name;

    @Schema(
            description = "email of the customer", example = "krishtech@email.com"
    )
    @NotEmpty(message = "Email address can not be null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "mobileNumber of the customer", example = "9960731973"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "mobileNumber must contain 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDto accountsDto;

    @Schema(
            description = "Loan details of the Customer"
    )
    private LoanDto loanDto;

    @Schema(
            description = "Card details of the Customer"
    )
    private CardsDto cardsDto;
}

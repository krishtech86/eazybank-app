package com.eazybytes.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(
        name = "Customer",
        description = "Schema to hold customer and account information"
)
@Data
public class CustomerDto {

    @Schema(description = "name od the customer", example = "Eazy Bytes")
    @NotEmpty(message = "name can not be empty")
    @Size(min = 5, max = 30, message = "Length of customer name in between 5 and 30")
    private String name;

    @Schema(description = "Email address of customer", example = "krishna12@gmail.com")
    @Email(message = "Email address should be of valid value")
    private String email;

    @Schema(description = "Mobile number of customer")
    @Pattern(regexp = "^$|[0-9]{10}", message = "mobile number must be of 10 digits")
    private String mobileNumber;

    @Schema(description = "Accounts details of customer")
    private AccountsDto accountsDto;
}

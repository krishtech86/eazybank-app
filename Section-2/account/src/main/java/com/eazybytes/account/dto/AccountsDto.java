package com.eazybytes.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Schema(
        name = "Accounts",
        description = "schema to hold Account information"
)
@Data
@Getter
@Setter
public class AccountsDto {

    @NotEmpty(message = "Account Number can not be empty or null")
    @Pattern(regexp = "^$|[0-9]{10}", message = "AccountNumber must be 10 digits")
    @Schema(
            description = "Account Number of Eazy bank account", example = "9087678909"
    )
    private Long accountNumber;

    @NotEmpty(message = "Account type can not be null or empty")
    @Schema(
            description = "Account Type of Eazy bank account", example = "Savings"
    )
    private String accountType;

    @NotEmpty(message = "branch address can not be null or empty")
    @Schema(
            description = "branch address of Eazy Bank", example = "123 NewYork"
    )
    private String branchAddress;




}

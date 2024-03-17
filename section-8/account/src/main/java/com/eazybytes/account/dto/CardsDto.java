package com.eazybytes.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Schema(
        name = "Cards",
        description = "Schema to hold cards information"
)
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardsDto {

    @Schema(description = "mobile numner of customer")
    @NotEmpty(message = "mobileNumber can not be null or empty")
    @Pattern(regexp = "^$|[0-9]{10}")
    private String mobileNumber;

    @Schema(description = "card number of account holder", example = "126578431298")
    @NotEmpty(message = "card number can not be null")
    @Pattern(regexp = "^$|[0-9]{12}",message = "Card numvber must be of 12 digits")
    private String cardNumber;

    @Schema(description = "Card type of customer")
    @NotEmpty(message = "cardType can not be empty")
    private String cardType;

    @Schema(description = "Total amount limit available against a card")
    @Positive(message = "Total card limit should be greater than zero")
    private int totalLimit;

    @Schema(description = "Total amount used by customer")
    @PositiveOrZero(message = "Total amount used should be equal or more than zero")
    private int amountUsed;

    @Schema(description = "Total available amount against a card")
    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    private int availableAmount;
}

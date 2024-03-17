package com.eazybytes.card.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Schema(
        name = "ResponseDto",
        description = "Schema to hold successful response information"
)
public class ResponseDto {

    @Schema(
            description = "Status code in Response"
    )
    private String statusCode;

    @Schema(
            description = "status message in response"
    )
    private String statusMeassge;
}

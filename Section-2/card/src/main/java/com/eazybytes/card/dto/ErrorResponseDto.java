package com.eazybytes.card.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "errorResponseDto",
        description = "Schema to hold error Response information"
)
public class ErrorResponseDto {

    @Schema(
            description = "api path invoked by client"
    )
    private String apiPath;

    @Schema(description = "error code representing error happened")
    private HttpStatus errorCode;

    @Schema(description = "error message representing error happened")
    private String errorMeassage;

    @Schema(description = "error time representing when error happened")
    private LocalDateTime errorTime;
}

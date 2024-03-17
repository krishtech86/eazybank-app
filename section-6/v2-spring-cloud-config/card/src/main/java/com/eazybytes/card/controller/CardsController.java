package com.eazybytes.card.controller;

import com.eazybytes.card.constant.CardsConstants;
import com.eazybytes.card.dto.CardContactInfoDto;
import com.eazybytes.card.dto.CardsDto;
import com.eazybytes.card.dto.ErrorResponseDto;
import com.eazybytes.card.dto.ResponseDto;
import com.eazybytes.card.service.IcardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Cards in EazyBank",
        description = "CRUD REST APIs in eazy bank to CREATE, UPDATE, FETCH and DELETE card details"
)
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardsController {

    private final IcardService icardService;

    public CardsController(IcardService icardService)
    {
        this.icardService = icardService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private CardContactInfoDto cardContactInfoDto;

    @Operation(
            summary = "Create Card Rest API",
            description = "REST API to create new Card inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam @Pattern(regexp = "^$|[0-9]{10}"
            ,message = "mobile Number must contain 10 numbers") String mobileNumber)
    {
        icardService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "fetch card details REST API",
            description = "REST API to fetch card details in EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status Ok"
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@Valid @Pattern(regexp = "^$|[0-9]{10}",
            message = "Mobile Number must contain 10 numbers") @RequestParam String mobileNumber)
    {
        CardsDto cardsDto = icardService.fetchCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @Operation(
            summary = "update card details REST API",
            description = "REST API to update card details in eazy bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardsDto cardsDto)
    {
        boolean isUpdated = icardService.updateCard(cardsDto);
        if(isUpdated)
        {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "delete card details REST API",
            description = "REST API to delete card details in eazy bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status Internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardsDetails(@RequestParam String mobileNumber)
    {
        boolean isDeleted = icardService.deleteCard(mobileNumber);
        if(isDeleted)
        {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        }

    }

    @Operation(
            summary = "get build information",
            description = "get build information that is deployed into cards microservices"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status Internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo()
    {
       return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }


    @Operation(
            summary = "get environment information",
            description = "get environment information which runs the cards microservices"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status Internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion()
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }



    @Operation(
            summary = "get contact information",
            description = "get contact info in case any issue arrived"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status Internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @GetMapping("/contact-info")
    public ResponseEntity<CardContactInfoDto> getContactInfo()
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardContactInfoDto);
    }
}

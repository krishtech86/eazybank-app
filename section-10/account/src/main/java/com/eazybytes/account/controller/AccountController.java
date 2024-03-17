package com.eazybytes.account.controller;

import com.eazybytes.account.constants.AccountsConstants;
import com.eazybytes.account.dto.AccountsContactInfoDto;
import com.eazybytes.account.dto.CustomerDto;
import com.eazybytes.account.dto.ErrorResponseDto;
import com.eazybytes.account.dto.ResponseDto;
import com.eazybytes.account.service.IAccountService;
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
        name = "CRUD REST API for Accounts in EazyBank",
        description = "CRUD REST APTs in EazyBank to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountController {

    private final IAccountService iAccountService;

    public AccountController(IAccountService iAccountService)
    {
        this.iAccountService = iAccountService;
    }
    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;
    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer & Account inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP status created"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
            )
            )
    }

    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto)
    {
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }


    @Operation(
            summary = "Fetch Account details REST API",
            description = "REST API to fetch Customer & Account details based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status ok"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }

    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Pattern(regexp = "^$|[0-9]{10}", message = "mobile number must be 10 digits")
                                                           String mobileNumber)
    {
        CustomerDto customerDto = iAccountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }


    @Operation(
            summary = "Update Account REST API",
            description = "REST API to update new Customer & Account details based on account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status ok"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }

    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Account REST API",
            description = "REST API to delete Customer & Account details based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status ok"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }

    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam @Pattern(regexp = "^$|[0-9]{10}",
            message = "mobile number must be 10 digits") String mobileNumber)
    {
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
        if (isDeleted)
        {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Get build information",
            description = "Get build information that is deployed into accounts microservices"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status ok"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }

    )
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo()
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }


    @Operation(
            summary = "Get version information",
            description = "Get version information which is installed into accounts microservices"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status ok"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }

    )
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion()
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }



    @Operation(
            summary = "Get contact information",
            description = "Contact info details that can be reached out in case of any issue"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status ok"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }

    )
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo()
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }
}

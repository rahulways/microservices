package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(  // <---------------------  i have made changes for      LEC 28 Enhancing documentation of REST APIs using @Tag , @Operation, @ApiResponse

        name = "CRUD REST APIs for Accounts in EazyBank",
        description = "CRUD REST APIs for Accounts in EazyBank to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@RequestMapping(path = "/api", produces = (MediaType.APPLICATION_JSON_VALUE))
@AllArgsConstructor
// using this we are autowiring using constructor this is best approach in comparision to auto wire using interfaces
@Validated  // <----------------------        LEC 24 Perform input data validations inside accounts

public class AccountsController {

    /* //     http://localhost:8080/sayHello
     //     http://localhost:8080/h2-console

     @GetMapping("sayHello")
     public String sayHello() {
         return "Hello  World  ";
     }  */
    @Autowired
    private IAccountsService iAccountsService;

    @Operation(   // <---------------------- here i have made changes for      LEC 28 Enhancing documentation of REST APIs using @Tag , @Operation, @ApiResponse
            summary = "Create Account REST API",
            description = "REST API to create new Customer & Account inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse( // <---------------------   i have made changes for      LEC 30 Enhancing documentation of REST APIs using @Schema & example data - Part 2
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })


    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)  // This status will go in the response Header
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    // ---------------------- BELOW I HAVE MADE CHANGES AS     LEC 20 READ API inside accounts microservice
    @Operation(   // <---------------------- here i have made changes for      LEC 28 Enhancing documentation of REST APIs using @Tag , @Operation, @ApiResponse
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch new Customer & Account  based on mobile number"

    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved account details"),
            @ApiResponse(responseCode = "400", description = "Invalid mobile number format"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 Digits")
                                                           @RequestParam String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    // ---------------------- Below i have made changes as      LEC 21 UPDATE API inside accounts microservice

    @Operation(   // <---------------------- here i have made changes for      LEC 28 Enhancing documentation of REST APIs using @Tag , @Operation, @ApiResponse
            summary = "Update Account Details REST API",
            description = "REST API to update  Customer & Account details based on mobile number"

    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(  // <---------------------- here i have made changes for      LEC 28 Enhancing documentation of REST APIs using @Tag , @Operation, @ApiResponse
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    // ---------------------- Below i have made changes for      LEC 22 DELETE API inside accounts microservice

    @Operation(   // <---------------------- here i have made changes for      LEC 28 Enhancing documentation of REST APIs using @Tag , @Operation, @ApiResponse
            summary = "Delete Account and Customer Details REST API",
            description = "REST API to delete  Customer & Account details based on a mobile number"

    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(  // <---------------------- here i have made changes for      LEC 28 Enhancing documentation of REST APIs using @Tag , @Operation, @ApiResponse
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })


    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 Digits")
                                                            @RequestParam String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

}

package com.eazybytes.accounts.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

@JsonPropertyOrder({
        "name",
        "email",
        "mobileNumber",
        "accountsDto"
})

@Schema( // <---------------------  i have made changes for      LEC 29 Enhancing documentation of REST APIs using @Schema & example data - Part 1

        name = "Customer",
        description = "Schema to hold Customer and Account information "
)

public class CustomerDto {

    @NotEmpty(message = "Name cannot be null or empty ")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    @Schema( // <---------------------  i have made changes for      LEC 29 Enhancing documentation of REST APIs using @Schema & example data - Part 1


            description = "Name of the customer", example="Eazy Bytes"
    )

    private String name;

    @Schema( // <---------------------  i have made changes for      LEC 29 Enhancing documentation of REST APIs using @Schema & example data - Part 1


            description = "Email address  of the customer", example="tutor@eazybytes.com"
    )

    @NotEmpty(message = "Email Address cannot be null or empty ")
    @Email(message = "Email address should be valid value")
    private String email;

    @Schema( // <---------------------  i have made changes for      LEC 29 Enhancing documentation of REST APIs using @Schema & example data - Part 1


            description = "Mobile Number of the customer", example="9334889156"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 Digits")
    private String mobileNumber;

    @Schema( // <---------------------  i have made changes for      LEC 29 Enhancing documentation of REST APIs using @Schema & example data - Part 1


            description = "Account details of the Customer"
    )
    // ---------------------- BELOW I HAVE MADE CHANGES AS     LEC 20 READ API inside accounts microservice
    private AccountsDto accountsDto;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public AccountsDto getAccountsDto() {
        return accountsDto;
    }

    public void setAccountsDto(AccountsDto accountsDto) {
        this.accountsDto = accountsDto;
    }
}

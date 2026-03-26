package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.message.Message;


@Schema( // <---------------------  i have made changes for      LEC 29 Enhancing documentation of REST APIs using @Schema & example data - Part 1

name = "Accounts",
        description = "Schema to hold Account information"
)

public class AccountsDto {
    @Schema( // <---------------------  i have made changes for      LEC 29 Enhancing documentation of REST APIs using @Schema & example data - Part 1

            description = "Account Number of Eazy Bank account" ,example = "3454433243"
    )
    @NotEmpty(message = "AccountNumber can not be null or empty ")// <----------------------        LEC 24 Perform input data validations inside accounts
    @Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 Digits")// <----------------------        LEC 24 Perform input data validations inside accounts
    private Long accountNumber;

    @Schema( // <---------------------  i have made changes for      LEC 29 Enhancing documentation of REST APIs using @Schema & example data - Part 1

            description = "Account Type of Eazy Bank account" , example = "Saving"
    )
    @NotEmpty(message = "AccountType can not be null or empty ") // <----------------------        LEC 24 Perform input data validations inside accounts
    private String accountType;


    @NotEmpty(message = "BranchAddress can not be null or empty ") // <----------------------        LEC 24 Perform input data validations inside accounts

    @Schema( // <---------------------  i have made changes for      LEC 29 Enhancing documentation of REST APIs using @Schema & example data - Part 1

            description = "Eazy Bank branch address ok", example = "123NewYork"
    )
    private String branchAddress;

    public AccountsDto(Long accountNumber, String accountType, String branchAddress) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
    }

    public AccountsDto() {
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getBranchAddress() {
        return branchAddress;
    }
}

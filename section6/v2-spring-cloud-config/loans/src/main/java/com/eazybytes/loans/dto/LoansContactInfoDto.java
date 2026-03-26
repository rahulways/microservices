package com.eazybytes.loans.dto;

// I have created this Dto  for  Lec 71.  ----------     Reading configurations using @ConfigurationProperties
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;



@ConfigurationProperties(prefix = "loans")
@Getter @Setter
public class LoansContactInfoDto {

    private String message;

    private Map<String, String> contactDetails;
    private List<String> onCallSupport;

}

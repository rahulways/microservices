package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Schema( // <---------------------  i have made changes for      LEC 29 Enhancing documentation of REST APIs using @Schema & example data - Part 1

        name = "Response",
        description = "Schema to hold Successful response information"
)

public class ResponseDto {

    @Schema( // <---------------------  i have made changes for      LEC 29 Enhancing documentation of REST APIs using @Schema & example data - Part 1

            description = "Status code in the response"
    )
    private String statusCode;

    @Schema( // <---------------------  i have made changes for      LEC 29 Enhancing documentation of REST APIs using @Schema & example data - Part 1

            description = "Status message in the response"
    )
    private String messageMsg;


    public ResponseDto( ) {

    }

    public ResponseDto(String statusCode, String messageMsg) {
        this.statusCode = statusCode;
        this.messageMsg = messageMsg;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessageMsg() {
        return messageMsg;
    }

    public void setMessageMsg(String messageMsg) {
        this.messageMsg = messageMsg;
    }
}
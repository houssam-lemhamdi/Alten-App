package com.product.trial.master.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response informations"
)
public class ErrorResponseDto {
    @Schema(
            description = "Api path of the error response"
    )
    private String apiPath;

    @Schema(
            description = "Error code of the error response"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message of the error response"
    )
    private String errorMessage;

    @Schema(
            description = "Error time of the error response"
    )
    private LocalDateTime errorTime;
}
